package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Remessa;

import java.util.List;

public interface RemessaService {

    List<Remessa> listar();
    Remessa listarPorId(Long id);
    Remessa cadastrar(Remessa remessa);
    Remessa atualizar(Long id, Remessa remessa);
    Remessa atualizarSituacao(Long id, String situacao);
    void remover(Long id);

}
