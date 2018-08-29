package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.dto.LancamentoNaoRecebidoEstatisticaCliente;
import br.prgomesr.debitoapi.dto.LancamentoRecebidoEstatisticaCliente;
import br.prgomesr.debitoapi.edi.bb.CodigoRetorno;
import br.prgomesr.debitoapi.model.*;
import br.prgomesr.debitoapi.repository.Lancamentos;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import br.prgomesr.debitoapi.service.exception.ClienteInexistenteInativoException;
import br.prgomesr.debitoapi.service.exception.LancamentosRemessaVaziaException;
import br.prgomesr.debitoapi.service.exception.RecursoVazioException;
import br.prgomesr.debitoapi.service.exception.RemessaNaoEncontradaException;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessa;
import br.prgomesr.debitoapi.util.retorno.bb.ArquivoRetorno;
import br.prgomesr.debitoapi.util.retorno.bb.Transacao;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private Lancamentos repository;

    @Autowired
    private GerarRemessa remessa;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private RemessaService remessaService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ClienteService clienteService;

    @Override
    public List<Lancamento> listar() {
        return repository.findAll();
    }

    @Override
    public List<Lancamento> listarDetalhes(LancamentoFilter filter) {
        return repository.filtrar(filter);
    }

    @Override
    public List<LancamentoProjection> listar(LancamentoFilter filter) {
        return repository.resumir(filter);
    }


    @Override
    public Page<LancamentoProjection> listarComPaginancao(LancamentoFilter filter, Pageable pageable) {
        return repository.resumirComPaginacao(filter, pageable);
    }


    @Override
    public ResponseEntity<Lancamento> listarPorId(Long id) {
        Lancamento lancamento = buscarRecursoExistente(id);
        return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
    }

    @Override
    public Lancamento cadastrar(Lancamento lancamento) {
        Cliente cliente = clienteService.buscarRegistroExistente(lancamento.getCliente().getId());
        if (cliente == null || cliente.isInativo()) {
            throw new ClienteInexistenteInativoException();
        }
        return repository.save(lancamento);
    }

    @Override
    public Lancamento atualizar(Long id, Lancamento lancamento) {
        Lancamento lancamentoSalvo = buscarRecursoExistente(id);
        BeanUtils.copyProperties(lancamento, lancamentoSalvo, "id");
        return repository.save(lancamentoSalvo);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void exportarRemessa(List<Lancamento> lancamentos) throws IOException {

        if (lancamentos.size() > 0) {
            // Instanciando convenio
            Convenio convenio = convenioService
                    .buscarRecursoExistente(lancamentos.get(0).getConvenio().getId());
            // Instanciando empresa
            Empresa empresa = empresaService
                    .listarPorId(lancamentos.get(0).getConvenio().getConta().getEmpresa().getId());
            remessa.exportarRemessa(lancamentos, convenio, empresa);

            convenioService.atualizarSequencial(convenio.getId(), convenio.getSequencial() + 1l);
        } else {
            throw new LancamentosRemessaVaziaException();
        }

    }

    @Override
    public byte[] pegarRemessa(Long id) throws IOException {

        Remessa remessa = remessaService.listarPorId(id);

        File file = new File("src/main/resources/remessa/" + remessa.getNome() + ".TXT");

        if (!file.exists()) {
            throw new RemessaNaoEncontradaException();
        } else {
            FileReader fileReader = new FileReader(file);
            byte[] arquivo = IOUtils.toByteArray(fileReader);
            fileReader.close();

            // regra para atualizar situacao da remessa
            String situacao = "BAIXADA";
            remessaService.atualizarSituacao(id, situacao);

            return arquivo;
        }
    }

    @Override
    public void cadastrarPorLote(LancamentoFilter filter, LocalDate vencimento) {
        if (filter != null) {
            List<Lancamento> lancamentos = listarDetalhes(filter);
            List<Lancamento> novosLancamentos = new ArrayList<>();
            lancamentos.forEach(lancamento -> {
                Lancamento novoLancamento = new Lancamento();

                novoLancamento.setValor(lancamento.getValor());
                novoLancamento.setVencimento(vencimento);
                novoLancamento.setSituacao(Situacao.PENDENTE);
                novoLancamento.setCliente(lancamento.getCliente());
                novoLancamento.setConvenio(lancamento.getConvenio());

                novosLancamentos.add(novoLancamento);
                if (novoLancamento.getCliente().isInativo()) {
                    novosLancamentos.remove(novoLancamento);
                }
            });
            novosLancamentos.forEach(lancamento -> {
                cadastrar(lancamento);
            });
        } else {
            throw new RecursoVazioException();
        }
    }

    @Override
    public void tratarRetorno(MultipartFile anexo, Long id) {
        try {
            if (!anexo.isEmpty()) {
                File file = File.createTempFile(anexo.getOriginalFilename(), "");
                if (file.exists()) {
                    Convenio convenio = convenioService.buscarRecursoExistente(id);

                    FileUtils.copyInputStreamToFile(anexo.getInputStream(), file);
                    atualizarLancamentosPeloArquivo(file, convenio);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ocorreu um erro ao tratar arquivo de retorno" + e.getMessage());
        }

    }

    @Override
    public byte[] relatorioLancametosRecebidos(LancamentoFilter filter) throws JRException {
        List<LancamentoRecebidoEstatisticaCliente> dados = repository.recebidoPorCliente(filter);

        JasperPrint jasperPrint = criarRelatorioRecebido(filter, dados);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private JasperPrint criarRelatorioRecebido(LancamentoFilter filter, List<LancamentoRecebidoEstatisticaCliente> dados) throws JRException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("DT_INICIO", java.sql.Date.valueOf(filter.getVencimentoDe()));
        parametros.put("DT_FIM", java.sql.Date.valueOf(filter.getVencimentoAte()));
        parametros.put("CONVENIO", filter.getConvenio());
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        InputStream inputStream = this.getClass().getResourceAsStream("/relatorios" +
                "/lancamentos-recebidos-por-cliente.jasper");

        return JasperFillManager.fillReport(inputStream, parametros,
                new JRBeanCollectionDataSource(dados));
    }

    private JasperPrint criarRelatorioNaoRecebido(LancamentoFilter filter, List<LancamentoNaoRecebidoEstatisticaCliente> dados) throws JRException {
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("DT_INICIO", java.sql.Date.valueOf(filter.getVencimentoDe()));
        parametros.put("DT_FIM", java.sql.Date.valueOf(filter.getVencimentoAte()));
        parametros.put("CONVENIO", filter.getConvenio());
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        InputStream inputStream = this.getClass().getResourceAsStream("/relatorios" +
                "/lancamentos-nao-recebidos-por-cliente.jasper");

        return JasperFillManager.fillReport(inputStream, parametros,
                new JRBeanCollectionDataSource(dados));
    }

    @Override
    public byte[] relatorioLancametosNaoRecebidos(LancamentoFilter filter) throws JRException {
        List<LancamentoNaoRecebidoEstatisticaCliente> dados = repository.naoRecebidoPorCliente(filter);

        JasperPrint jasperPrint = criarRelatorioNaoRecebido(filter, dados);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private void atualizarLancamentosPeloArquivo(File file, Convenio convenio) {
        ArquivoRetorno retorno = new ArquivoRetorno(file, convenio);
        LancamentoFilter filter = new LancamentoFilter();
        filter.setConvenio(convenio.getId());
        List<Transacao> transacoes = retorno.getTransacoes();
        transacoes.forEach(transacao -> {
            Lancamento lancamento = buscarRecursoExistente(Long.valueOf(transacao.getCodLancamento()));
            if (transacao.getCodRetorno().equalsIgnoreCase(CodigoRetorno.EFETUADO.getDescricao())) {
                lancamento.setPagamento(toLocalDate(transacao.getDataVencimento()));
                lancamento.setValorPago(transacao.getValorDoDebito());
                lancamento.setSituacao(Situacao.PAGO);
            }
            lancamento.setCodigoRetorno(Long.valueOf(transacao.getCodRetorno()));
            cadastrar(lancamento);
        });
    }

    private Lancamento buscarRecursoExistente(Long id) {
        Lancamento lancamento = repository.getOne(id);
        if (lancamento == null) {
            throw new EntityNotFoundException();
        }
        return lancamento;
    }

    public static LocalDate toLocalDate(Date data) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = data.toInstant();
        return instant.atZone(defaultZoneId).toLocalDate();
    }

}
