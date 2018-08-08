package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.repository.Convenios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConvenioServiceImpl implements ConvenioService {

    @Autowired
    private Convenios repository;

    @Override
    public List<Convenio> listar() {
        return repository.findAll();
    }

    @Override
    public Convenio listarPorId(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Convenio cadastrar(Convenio convenio) {
        return repository.save(convenio);
    }

    @Override
    public Convenio atualizar(Long id, Convenio convenio) {
        convenio = listarPorId(id);
        return repository.save(convenio);
    }

    @Override
    public void atualizarSequencial(Long id, Long sequencial) {
        Convenio convenio = listarPorId(id);
        convenio.setSequencial(sequencial);
        repository.save(convenio);
    }

    @Override
    public void remover(Long id) {
        repository.deleteById(id);
    }
}
