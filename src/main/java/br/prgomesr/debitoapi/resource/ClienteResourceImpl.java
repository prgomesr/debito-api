package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResourceImpl implements ClienteResource {

    @Autowired
    private ClienteService service;

    @Override
    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @Override
    @GetMapping("/{id}")
    public Cliente listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        return service.cadastrar(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente cliente) {
        return service.atualizar(id, cliente);
    }

    @Override
    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente atualizarAtivo(@PathVariable Long id, @RequestBody Boolean ativo) {
        return service.atualizarAtivo(id, ativo);
    }

    @Override
    public void remover(Long id) {
        service.remover(id);
    }
}
