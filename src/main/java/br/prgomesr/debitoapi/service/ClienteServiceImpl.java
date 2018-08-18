package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.repository.Clientes;
import org.springframework.beans.BeanUtils;
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
        return buscarRegistroExistente(id);
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente clientesalvo = buscarRegistroExistente(id);
        BeanUtils.copyProperties(cliente, clientesalvo, "id");
        return repository.save(clientesalvo);
    }

    @Override
    public Cliente atualizarAtivo(Long id, Boolean ativo) {
        Cliente cliente;
        if (id == null) {
            throw new EmptyResultDataAccessException(1);
        } else {
            cliente = buscarRegistroExistente(id);
            cliente.setAtivo(ativo);
        }
        return cadastrar(cliente);
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
