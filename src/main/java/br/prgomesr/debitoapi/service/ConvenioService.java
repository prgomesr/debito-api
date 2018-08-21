package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Convenio;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConvenioService {

    List<Convenio> listar();
    ResponseEntity <Convenio> listarPorId(Long id);
    Convenio cadastrar(Convenio convenio);
    Convenio atualizar(Long id, Convenio convenio);
    void atualizarSequencial(Long id, Long sequencial);
    void remover(Long id);
    Convenio buscarRecursoExistente(Long id);
}
