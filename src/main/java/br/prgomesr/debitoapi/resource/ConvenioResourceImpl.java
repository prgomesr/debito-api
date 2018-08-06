package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ConvenioResourceImpl implements ConvenioResource {

    @Autowired
    private ConvenioService service;

    @Override
    public List<Convenio> listar() {
        return service.listar();
    }

    @Override
    public Convenio listarPorId(Long id) {
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
