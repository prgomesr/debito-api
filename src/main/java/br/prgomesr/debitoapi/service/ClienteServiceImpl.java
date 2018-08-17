package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private Clientes repository;

    @Override
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @Override
    public Cliente listarPorId(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        cliente = listarPorId(id);
        return repository.save(cliente);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Cliente buscarRegistroExistente(Long id) {
        Cliente cliente = repository.getOne(id);
        if (cliente == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return cliente;
    }
}
