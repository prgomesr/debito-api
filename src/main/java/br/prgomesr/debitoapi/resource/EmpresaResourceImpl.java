package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaResourceImpl implements EmpresaResource {

    @Autowired
    private EmpresaService service;

    @Override
    @GetMapping
    public List<Empresa> listar() {
        return service.listar();
    }

    @Override
    public Empresa listarPorId(Long id) {
        return service.listarPorId(id);
    }

    @Override
    public Empresa cadastrar(Empresa empresa) {
        return null;
    }

    @Override
    public Empresa atualizar(Long id, Empresa empresa) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
