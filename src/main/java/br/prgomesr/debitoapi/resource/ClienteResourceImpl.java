package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.event.RecursoCriadoEvent;
import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResourceImpl implements ClienteResource {

    @Autowired
    private ClienteService service;

    @Autowired
    private ApplicationEventPublisher publisher;

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
    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
        Cliente clienteSalvo = service.cadastrar(cliente);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvo = service.atualizar(id, cliente);
        return ResponseEntity.ok(clienteSalvo);
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
