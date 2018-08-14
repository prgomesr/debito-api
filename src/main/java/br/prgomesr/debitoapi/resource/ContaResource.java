package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Conta;

import java.util.List;

public interface ContaResource {

    List<Conta> listar();
    Conta listarPorId(Long id);
    Conta cadastrar(Conta conta);
    Conta atualizar(Long id, Conta conta);
    void remover(Long id);
}
