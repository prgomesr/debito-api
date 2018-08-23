package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.model.Remessa;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface LancamentoResource {

    List<Lancamento> listarDetalhes(LancamentoFilter filter);
    List<LancamentoProjection> listar(LancamentoFilter filter);
    Page<LancamentoProjection> listarComPaginacao(LancamentoFilter filter, Pageable pageable);
    ResponseEntity<Lancamento> listarPorId(Long id);
    ResponseEntity<Lancamento> cadastrar(Lancamento lancamento, HttpServletResponse response);
    ResponseEntity <Lancamento> atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    ResponseEntity exportarRemessa(LancamentoFilter filter) throws IOException;
    ResponseEntity<?> remessa(Long id) throws IOException;
    void cadastrarPorLote(LancamentoFilter filter, LocalDate vencimento);
    String uploadArquivo(@RequestParam MultipartFile anexo) throws IOException;

}
