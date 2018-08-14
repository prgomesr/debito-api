package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Conta;
import br.prgomesr.debitoapi.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaResourceImpl implements ContaResource {

    @Autowired
    private ContaService service;

    @Override
    @GetMapping
    public List<Conta> listar() {
        return service.listar();
    }

    @Override
    public Conta listarPorId(Long id) {
        return service.listarPorId(id);
    }

    @Override
    public Conta cadastrar(Conta conta) {
        return service.cadastrar(conta);
    }

    @Override
    public Conta atualizar(Long id, Conta conta) {
        return service.atualizar(id, conta);
    }

    @Override
    public void remover(Long id) {
        service.remover(id);
    }
}
