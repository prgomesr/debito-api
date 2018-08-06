package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Lancamento;

import java.util.List;

public interface LancamentoResource {

    List<Lancamento> listar();
    Lancamento listarPorId(Long id);
    Lancamento cadastrar(Lancamento lancamento);
    Lancamento atualizar(Long id, Lancamento lancamento);
    void remover(Long id);
    public void exportarRemessa();

}
