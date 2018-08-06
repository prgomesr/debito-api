package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Empresa;

import java.util.List;

public interface EmpresaResource {

    List<Empresa> listar();
    Empresa listarPorId(Long id);
    Empresa cadastrar(Empresa empresa);
    Empresa atualizar(Long id, Empresa empresa);
    void remover(Long id);
}
