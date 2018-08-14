package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Conta;
import br.prgomesr.debitoapi.repository.Contas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    @Autowired
    private Contas repository;

    @Override
    @GetMapping
    public List<Conta> listar() {
        return repository.findAll();
    }

    @Override
    public Conta listarPorId(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Conta cadastrar(Conta conta) {
        return repository.save(conta);
    }

    @Override
    public Conta atualizar(Long id, Conta conta) {
        conta = listarPorId(id);
        return repository.save(conta);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
