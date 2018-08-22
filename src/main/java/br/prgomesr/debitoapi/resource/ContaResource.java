package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Conta;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ContaResource {

    List<Conta> listar();
    Conta listarPorId(Long id);
    ResponseEntity <Conta> cadastrar(Conta conta, HttpServletResponse response);
    ResponseEntity <Conta> atualizar(Long id, Conta conta);
    void remover(Long id);
}
