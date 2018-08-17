package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.model.Remessa;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface LancamentoResource {

    List<Lancamento> listarDetalhes(LancamentoFilter filter);
    List<LancamentoProjection> listar(LancamentoFilter filter);
    ResponseEntity<Lancamento> listarPorId(Long id);
    ResponseEntity<Lancamento> cadastrar(Lancamento lancamento, HttpServletResponse response);
    ResponseEntity <Lancamento> atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    public ResponseEntity exportarRemessa(LancamentoFilter filter) throws IOException;
    public ResponseEntity<?> remessa(Long id) throws IOException;

}
