package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Remessa;
import br.prgomesr.debitoapi.service.RemessaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remessas")
public class RemessaResourceImpl implements RemessaResource {

    @Autowired
    private RemessaService service;

    @Override
    @GetMapping
    public List<Remessa> listar() {
        return service.listar();
    }

    @Override
    public Remessa listarPorId(Long id) {
        return service.listarPorId(id);
    }

    @Override
    public Remessa cadastrar(Remessa remessa) {
        return service.cadastrar(remessa);
    }

    @Override
    public Remessa atualizar(Long id, Remessa remessa) {
        return service.atualizar(id, remessa);
    }

    @Override
    public void remover(Long id) {
        service.remover(id);
    }
}
