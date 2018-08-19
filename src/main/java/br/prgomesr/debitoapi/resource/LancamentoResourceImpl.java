package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.event.RecursoCriadoEvent;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import br.prgomesr.debitoapi.service.ConvenioService;
import br.prgomesr.debitoapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResourceImpl implements LancamentoResource {

    @Autowired
    private LancamentoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    @GetMapping(params = "detalhes")
    public List<Lancamento> listarDetalhes(LancamentoFilter filter) {
        return service.listarDetalhes(filter);
    }

    @Override
    @GetMapping
    public List<LancamentoProjection> listar(LancamentoFilter filter) {
        return service.listar(filter);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Lancamento> listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<Lancamento> cadastrar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = service.cadastrar(lancamento);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento lancamento) {
        Lancamento lancamentoSalvo = service.atualizar(id, lancamento);
        return ResponseEntity.ok().body(lancamentoSalvo);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        service.remover(id);
    }

    @Override
    @GetMapping("gerar-remessa")
    public ResponseEntity exportarRemessa(LancamentoFilter filter) throws IOException {
        List<Lancamento> lancamentos = listarDetalhes(filter);

        service.exportarRemessa(lancamentos);

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentos);
    }

    @Override
    @GetMapping("/{id}/pegar-remessa")
    public ResponseEntity<Object> remessa(@PathVariable Long id) throws IOException {
        byte[] arquivo = service.pegarRemessa(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body(arquivo);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("cadastrar-por-lote")
    public void cadastrarPorLote(LancamentoFilter filter, @RequestBody LocalDate vencimento) {
        service.cadastrarPorLote(filter, vencimento);
    }


}
