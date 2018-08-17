package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.service.ConvenioService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convenios")
public class ConvenioResourceImpl implements ConvenioResource {

    @Autowired
    private ConvenioService service;

    @Override
    @GetMapping
    public List<Convenio> listar() {
        return service.listar();
    }

    @Override
    @GetMapping("/{id}")
    public Convenio listarPorId(@PathVariable Long id) {
        return service.listarPorId(id);
    }

    @Override
    public Convenio cadastrar(Convenio convenio) {
        return service.cadastrar(convenio);
    }

    @Override
    public Convenio atualizar(Long id, Convenio convenio) {
        return service.atualizar(id, convenio);
    }

    @Override
    public void remover(Long id) {
        service.remover(id);
    }
}
