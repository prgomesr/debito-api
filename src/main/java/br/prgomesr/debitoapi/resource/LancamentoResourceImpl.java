package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.Lancamentos;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.service.ConvenioService;
import br.prgomesr.debitoapi.service.EmpresaService;
import br.prgomesr.debitoapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamentos")
@CrossOrigin("http://localhost:4200")
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

    @Override
    @GetMapping
    public List<Lancamento> listarPorLote(LancamentoFilter filter) {
        return service.listarPorLote(filter);
    }

    @Override
    public List<Lancamento> filtrarPorLote(String lote) {
        return repository.filtrarPorLote(lote);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity <Lancamento> listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @Override
    @PostMapping
    public ResponseEntity<Lancamento> cadastrar(@RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = service.cadastrar(lancamento);

//        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getId()));

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
    @GetMapping("gerarRemessa")
    public void exportarRemessa() {
        LancamentoFilter filter = new LancamentoFilter();
        List<Lancamento> lancamentos = filtrarPorLote("5");
        Convenio convenio = convenioService.listarPorId(2L);
        Empresa empresa = empresaService.listarPorId(1L);
        try {
            service.exportarRemessa(lancamentos, convenio, empresa);
        } catch (IOException e) {
            throw new IllegalArgumentException("erro "+e);
        }
//        System.out.println(lancamentos);
    }

    @Override
    @GetMapping("pegar-remessa")
    public ResponseEntity<byte[]> remessa(String nome) throws IOException{
        nome = "55552_84";
        return service.remessa(nome);
    }

}
