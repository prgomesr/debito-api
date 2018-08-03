package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResourceImpl implements LancamentoResource {

    @Autowired
    private LancamentoService service;

    @Override
    @GetMapping
    public List<Lancamento> listar() {
        return service.listar();
    }

    @Override
    public Lancamento listarPorId(Long id) {
        return null;
    }

    @Override
    public Lancamento cadastrar(Lancamento lancamento) {
        return null;
    }

    @Override
    public Lancamento atualizar(Long id, Lancamento lancamento) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }

    @Override
    @GetMapping("/gerarRemessa")
    public void gerarRemessa(List<Lancamento> lancamentos) throws IOException {
        service.gerarRemessa(lancamentos);
    }
}
