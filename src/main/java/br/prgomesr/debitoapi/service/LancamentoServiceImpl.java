package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.*;
import br.prgomesr.debitoapi.repository.Lancamentos;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import br.prgomesr.debitoapi.service.exception.ClienteInexistenteInativoException;
import br.prgomesr.debitoapi.service.exception.LancamentosRemessaVaziaException;
import br.prgomesr.debitoapi.service.exception.RecursoVazioException;
import br.prgomesr.debitoapi.service.exception.RemessaNaoEncontradaException;
import br.prgomesr.debitoapi.util.remessa.bb.CriarIdentificadorBanco;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessa;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessaInclusao;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    public List<Lancamento> listarDetalhes(LancamentoFilter filter) {
        return repository.filtrar(filter);
    }

    @Override
    public List<LancamentoProjection> listar(LancamentoFilter filter) {
        return repository.resumir(filter);
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
                    .listarPorId(lancamentos.get(0).getConvenio().getId());
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

    private Lancamento buscarRecursoExistente(Long id) {
        Lancamento lancamento = repository.getOne(id);
        if (lancamento == null) {
            throw new EntityNotFoundException();
        }
        return lancamento;
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
            });
            novosLancamentos.forEach(lancamento -> {
                cadastrar(lancamento);
            });
        } else {
            throw new RecursoVazioException();
        }
    }

}
