package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Convenio;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ConvenioResource {

    List<Convenio> listar();
    Convenio listarPorId(Long id);
    ResponseEntity <Convenio> cadastrar(Convenio convenio, HttpServletResponse response);
    ResponseEntity <Convenio> atualizar(Long id, Convenio convenio);
    void remover(Long id);
}
