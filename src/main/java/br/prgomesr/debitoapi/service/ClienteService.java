package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> listar();
    Cliente listarPorId(Long id);
    Cliente cadastrar(Cliente cliente);
    Cliente atualizar(Long id, Cliente cliente);
    void remover(Long id);
    public Cliente buscarRegistroExistente(Long id);
}
