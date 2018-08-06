package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Convenio;

import java.util.List;

public interface ConvenioResource {

    List<Convenio> listar();
    Convenio listarPorId(Long id);
    Convenio cadastrar(Convenio convenio);
    Convenio atualizar(Long id, Convenio convenio);
    void remover(Long id);
}
