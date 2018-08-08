package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Cliente;

import java.util.List;

public interface ClienteResource {

    List<Cliente> listar();
    Cliente listarPorId(Long id);
    Cliente cadastrar(Cliente cliente);
    Cliente atualizar(Long id, Cliente cliente);
    void remover(Long id);
}
