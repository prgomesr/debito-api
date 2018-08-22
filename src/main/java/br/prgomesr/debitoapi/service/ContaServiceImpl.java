package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Conta;
import br.prgomesr.debitoapi.repository.Contas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Conta> listarPorId(Long id) {
        Conta conta = buscarRecursoExistente(id);
        return conta != null ? ResponseEntity.ok(conta) : ResponseEntity.notFound().build();
    }

    @Override
    public Conta cadastrar(Conta conta) {
        return repository.save(conta);
    }

    @Override
    public Conta atualizar(Long id, Conta conta) {
        Conta contaSalva = buscarRecursoExistente(id);
        BeanUtils.copyProperties(conta, contaSalva, "id");
        return repository.save(contaSalva);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Conta buscarRecursoExistente(Long id) {
        Conta conta = repository.getOne(id);
        if (conta == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return conta;
    }
}
