package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface LancamentoService {

    List<Lancamento> listarPorLote(LancamentoFilter filter);
    ResponseEntity <Lancamento> listarPorId(Long id);
    Lancamento cadastrar(Lancamento lancamento);
    Lancamento atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    public void exportarRemessa(List<Lancamento> lancamentos, Convenio convenio, Empresa empresa) throws IOException;
    public ResponseEntity<byte []> remessa(String nome) throws IOException;

}
