package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.repository.Empresas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private Empresas repository;

    @Override
    public List<Empresa> listar() {
        return repository.findAll();
    }

    @Override
    public Empresa listarPorId(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Empresa cadastrar(Empresa empresa) {
        return null;
    }

    @Override
    public Empresa atualizar(Long id, Empresa empresa) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
