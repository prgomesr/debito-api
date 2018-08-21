package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.event.RecursoCriadoEvent;
import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/convenios")
public class ConvenioResourceImpl implements ConvenioResource {

    @Autowired
    private ConvenioService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    @GetMapping
    public List<Convenio> listar() {
        return service.listar();
    }

    @Override
    @GetMapping("/{id}")
    public Convenio listarPorId(@PathVariable Long id) {
        return service.buscarRecursoExistente(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<Convenio> cadastrar(@Valid @RequestBody Convenio convenio, HttpServletResponse response) {
        Convenio convenioSalvo = service.cadastrar(convenio);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, convenioSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(convenioSalvo);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Convenio> atualizar(@PathVariable Long id, @Valid @RequestBody Convenio convenio) {
        Convenio convenioSalvo = service.atualizar(id, convenio);
        return ResponseEntity.ok().body(convenioSalvo);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }
}
