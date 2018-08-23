package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.event.RecursoCriadoEvent;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import br.prgomesr.debitoapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR')")
    public List<Lancamento> listarDetalhes(LancamentoFilter filter) {
        return service.listarDetalhes(filter);
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR')")
    public List<LancamentoProjection> listar(LancamentoFilter filter) {
        return service.listar(filter);
    }

    @Override
    @GetMapping("/paginacao")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR')")
    public Page<LancamentoProjection> listarComPaginacao(LancamentoFilter filter, Pageable pageable) {
        return service.listarComPaginancao(filter, pageable);
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR')")
    public ResponseEntity<Lancamento> listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR')")
    public ResponseEntity<Lancamento> cadastrar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = service.cadastrar(lancamento);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR')")
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
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR')")
    public ResponseEntity exportarRemessa(LancamentoFilter filter) throws IOException {
        List<Lancamento> lancamentos = listarDetalhes(filter);

        service.exportarRemessa(lancamentos);

        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentos);
    }

    @Override
    @GetMapping("/{id}/pegar-remessa")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR')")
    public ResponseEntity<Object> remessa(@PathVariable Long id) throws IOException {
        byte[] arquivo = service.pegarRemessa(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body(arquivo);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("cadastrar-por-lote")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR')")
    public void cadastrarPorLote(LancamentoFilter filter, @RequestBody LocalDate vencimento) {
        service.cadastrarPorLote(filter, vencimento);
    }

    @Override
    @PostMapping("/anexo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR')")
    public void uploadArquivo(@RequestParam MultipartFile anexo) {
        service.tratarRetorno(anexo);
    }

}
