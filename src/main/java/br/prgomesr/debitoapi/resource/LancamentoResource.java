package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface LancamentoResource {

    List<Lancamento> listarPorLote(LancamentoFilter filter);
    public List<Lancamento> filtrarPorLote(String lote);
    ResponseEntity<Lancamento> listarPorId(Long id);
    ResponseEntity<Lancamento> cadastrar(Lancamento lancamento, HttpServletResponse response);
    Lancamento atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    public void exportarRemessa();
    public ResponseEntity<byte []> remessa(String nome) throws IOException;

}
