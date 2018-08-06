package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Empresa;

import java.util.List;

public interface EmpresaService {

    List<Empresa> listar();
    Empresa listarPorId(Long id);
    Empresa cadastrar(Empresa empresa);
    Empresa atualizar(Long id, Empresa empresa);
    void remover(Long id);
}
