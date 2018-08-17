package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.event.RecursoCriadoEvent;
import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.model.Remessa;
import br.prgomesr.debitoapi.repository.Lancamentos;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import br.prgomesr.debitoapi.service.ConvenioService;
import br.prgomesr.debitoapi.service.EmpresaService;
import br.prgomesr.debitoapi.service.LancamentoService;
import br.prgomesr.debitoapi.service.RemessaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResourceImpl implements LancamentoResource {

    @Autowired
    private LancamentoService service;

    @Autowired
    private Lancamentos repository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private RemessaService remessaService;

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
    public ResponseEntity <Lancamento> listarPorId(@PathVariable Long id) {
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
    public Lancamento atualizar(Long id, Lancamento lancamento) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }

    @Override
    @GetMapping("gerar-remessa")
    public ResponseEntity exportarRemessa(LancamentoFilter filter) throws IOException{
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
            throw new IllegalArgumentException("erro "+e);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentos);
    }

    @Override
    @GetMapping("/{id}/pegar-remessa")
    public ResponseEntity<byte[]> remessa(@PathVariable Long id) throws IOException{

        return service.pegarRemessa(id);
    }

}
