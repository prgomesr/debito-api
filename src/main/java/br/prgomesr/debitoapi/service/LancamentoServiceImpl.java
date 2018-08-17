package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.model.Remessa;
import br.prgomesr.debitoapi.repository.Lancamentos;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessa;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessaInclusao;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private Lancamentos repository;

    @Autowired
    private GerarRemessa remessa;

    @Autowired
    private GerarRemessaInclusao remessaInclusao;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private RemessaService remessaService;

    @Override
    public List<Lancamento> listarPorLote(LancamentoFilter filter) {
        return repository.filtrar(filter);
    }

    @Override
    public List<LancamentoProjection> listar(LancamentoFilter filter) {
        return repository.resumir(filter);
    }


    @Override
    public ResponseEntity <Lancamento> listarPorId(Long id) {
        Optional <Lancamento> lancamento = buscarRecursoExistente(id);
        return lancamento.isPresent() ? ResponseEntity.ok(lancamento.get()) : ResponseEntity.notFound().build();
    }

    @Override
    public Lancamento cadastrar(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    @Override
    public Lancamento atualizar(Long id, Lancamento lancamento) {
//        lancamento = buscarRecursoExistente(id);
        return repository.save(lancamento);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void exportarRemessa(List<Lancamento> lancamentos, Convenio convenio, Empresa empresa) throws IOException {

        remessa.exportarRemessa(lancamentos, convenio, empresa);

        convenioService.atualizarSequencial(convenio.getId(), convenio.getSequencial()+1l);

    }

    @Override
    public ResponseEntity<byte[]> pegarRemessa(Long id) throws IOException {
//        regra para atualizar situacao da remessa
        Remessa remessa = remessaService.listarPorId(id);
        String situacao = "BAIXADA";
        remessaService.atualizarSituacao(id, situacao);
        InputStream stream = this.getClass().getResourceAsStream("/remessa/" + remessa.getNome() + ".TXT");
        byte [] arquivo = IOUtils.toByteArray(stream);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body(arquivo);
    }

    private Optional <Lancamento> buscarRecursoExistente(Long id) {
        Optional <Lancamento> lancamento = repository.findById(id);
//        if (!lancamento.isPresent()) {
//            throw  new IllegalArgumentException();
//        }
        return lancamento;
    }

}
