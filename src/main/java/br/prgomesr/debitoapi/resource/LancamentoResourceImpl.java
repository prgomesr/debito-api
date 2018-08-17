package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.event.RecursoCriadoEvent;
import br.prgomesr.debitoapi.exceptionhandler.DebitoExceptionHandler.Erro;
import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import br.prgomesr.debitoapi.service.ConvenioService;
import br.prgomesr.debitoapi.service.EmpresaService;
import br.prgomesr.debitoapi.service.LancamentoService;
import br.prgomesr.debitoapi.service.exception.ClienteInexistenteInativoException;
import br.prgomesr.debitoapi.service.exception.RemessaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResourceImpl implements LancamentoResource {

    @Autowired
    private LancamentoService service;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private MessageSource messageSource;


    @Override
    @GetMapping(params = "detalhes")
    public List<Lancamento> listarDetalhes(LancamentoFilter filter) {
        return service.listarPorLote(filter);
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
        // Instanciando convenio
        Convenio convenio = convenioService
                .listarPorId(lancamentos.get(0).getConvenio().getId());
        // Instanciando empresa
        Empresa empresa = empresaService
                .listarPorId(lancamentos.get(0).getConvenio().getConta().getEmpresa().getId());
        try {
            service.exportarRemessa(lancamentos, convenio, empresa);
        } catch (IOException e) {
            throw new IllegalArgumentException("erro " + e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentos);
    }

    @Override
    @GetMapping("/{id}/pegar-remessa")
    public ResponseEntity<Object> remessa(@PathVariable Long id) throws IOException {
        byte[] arquivo = service.pegarRemessa(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body(arquivo);
    }

    @ExceptionHandler({ClienteInexistenteInativoException.class})
    public ResponseEntity<Object> handleClienteInexistenteInativoException(ClienteInexistenteInativoException ex) {
        String mensagemUsuario = messageSource.getMessage("cliente.inexistente-ou-inativo", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler({RemessaNaoEncontradaException.class})
    public ResponseEntity<Object> handleRemessaNaoEncontradaException(RemessaNaoEncontradaException ex) {
        String mensagemUsuario = messageSource.getMessage("remessa.inexistente", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }

}
