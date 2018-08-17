package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Cliente listarPorId(Long id) {
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
    public void remover(Long id) {
        service.remover(id);
    }
}
