package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Remessa;
import br.prgomesr.debitoapi.repository.Remessas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemessaServiceImpl implements RemessaService {

    @Autowired
    private Remessas repository;

    @Override
    public List<Remessa> listar() {
        return repository.findAll();
    }

    @Override
    public Remessa listarPorId(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Remessa cadastrar(Remessa remessa) {
        return repository.save(remessa);
    }

    @Override
    public Remessa atualizar(Long id, Remessa remessa) {
        remessa = listarPorId(id);
        return repository.save(remessa);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
