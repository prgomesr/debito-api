package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmpresaResourceImpl implements EmpresaResource {

    @Autowired
    private EmpresaService service;

    @Override
    public List<Empresa> listar() {
        return service.listar();
    }

    @Override
    public Empresa listarPorId(Long id) {
        return service.listarPorId(id);
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
