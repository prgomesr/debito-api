package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface LancamentoService {

    List<Lancamento> listar();
    List<Lancamento> listarDetalhes(LancamentoFilter filter);
    List<LancamentoProjection> listar(LancamentoFilter filter);
    Page<LancamentoProjection> listarComPaginancao(LancamentoFilter filter, Pageable pageable);
    ResponseEntity <Lancamento> listarPorId(Long id);
    Lancamento cadastrar(Lancamento lancamento);
    Lancamento atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    void exportarRemessa(List<Lancamento> lancamentos) throws IOException;
    byte [] pegarRemessa(Long id) throws IOException;
    void cadastrarPorLote(LancamentoFilter filter, LocalDate vencimento);
    void tratarRetorno(MultipartFile file, Long id);
    byte[] relatorioLancametosRecebidos(LancamentoFilter filter) throws JRException;
    byte[] relatorioLancametosNaoRecebidos(LancamentoFilter filter) throws JRException;
}
