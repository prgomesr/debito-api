package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Conta;

import java.util.List;

public interface ContaService {

    List<Conta> listar();
    Conta listarPorId(Long id);
    Conta cadastrar(Conta conta);
    Conta atualizar(Long id, Conta conta);
    void remover(Long id);
}
