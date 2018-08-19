package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface LancamentoService {

    List<Lancamento> listarDetalhes(LancamentoFilter filter);
    List<LancamentoProjection> listar(LancamentoFilter filter);
    ResponseEntity <Lancamento> listarPorId(Long id);
    Lancamento cadastrar(Lancamento lancamento);
    Lancamento atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    public void exportarRemessa(List<Lancamento> lancamentos) throws IOException;
    public byte [] pegarRemessa(Long id) throws IOException;
    public void cadastrarPorLote(LancamentoFilter filter, LocalDate vencimento);

}
