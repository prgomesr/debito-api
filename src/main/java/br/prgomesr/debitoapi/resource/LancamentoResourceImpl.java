package br.prgomesr.debitoapi.resource;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.service.ConvenioService;
import br.prgomesr.debitoapi.service.EmpresaService;
import br.prgomesr.debitoapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResourceImpl implements LancamentoResource {

    @Autowired
    private LancamentoService service;

    @Autowired
    private ConvenioService convenioService;

    @Autowired
    private EmpresaService empresaService;

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
    @GetMapping("gerarRemessa")
    public void exportarRemessa() {
        List<Lancamento> lancamentos = lancamentos = listar();
        Convenio convenio = convenioService.listarPorId(2L);
        Empresa empresa = empresaService.listarPorId(1L);
        try {
            service.exportarRemessa(lancamentos, convenio, empresa);
        } catch (IOException e) {
            throw new IllegalArgumentException("erro "+e);
        }
//        System.out.println(lancamentos);
    }

}
