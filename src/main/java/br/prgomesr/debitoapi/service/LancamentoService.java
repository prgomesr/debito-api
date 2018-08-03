package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Lancamento;

import java.io.IOException;
import java.util.List;

public interface LancamentoService {

    List<Lancamento> listar();
    Lancamento listarPorId(Long id);
    Lancamento cadastrar(Lancamento lancamento);
    Lancamento atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    void gerarRemessa(List<Lancamento> lancamentos) throws IOException;

}
