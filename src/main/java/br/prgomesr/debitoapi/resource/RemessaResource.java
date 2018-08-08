package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Remessa;

import java.util.List;

public interface RemessaResource {

    List<Remessa> listar();
    Remessa listarPorId(Long id);
    Remessa cadastrar(Remessa remessa);
    Remessa atualizar(Long id, Remessa remessa);
    void remover(Long id);
}
