package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.event.RecursoCriadoEvent;
import br.prgomesr.debitoapi.model.Conta;
import br.prgomesr.debitoapi.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaResourceImpl implements ContaResource {

    @Autowired
    private ContaService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    @GetMapping
    public List<Conta> listar() {
        return service.listar();
    }

    @Override
    @GetMapping("/{id}")
    public Conta listarPorId(@PathVariable Long id) {
        return service.buscarRecursoExistente(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<Conta> cadastrar(@Valid @RequestBody Conta conta, HttpServletResponse response) {
        Conta contaSalva = service.cadastrar(conta);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, contaSalva.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(contaSalva);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @Valid @RequestBody Conta conta) {
        Conta contaSalva = service.atualizar(id, conta);
        return ResponseEntity.ok(contaSalva);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
