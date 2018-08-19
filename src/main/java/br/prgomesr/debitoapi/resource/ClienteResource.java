package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Cliente;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ClienteResource {

    List<Cliente> listar();
    Cliente listarPorId(Long id);
    ResponseEntity <Cliente> cadastrar(Cliente cliente, HttpServletResponse response);
    ResponseEntity <Cliente> atualizar(Long id, Cliente cliente);
    Cliente atualizarAtivo(Long id, Boolean ativo);
    void remover(Long id);
}
