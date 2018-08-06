package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.Lancamentos;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private Lancamentos repository;

    @Autowired
    private GerarRemessa remessa;

    @Autowired
    private ConvenioService convenioService;

    @Override
    public List<Lancamento> listar() {
        return repository.findAll();
    }

    @Override
    public Lancamento listarPorId(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Lancamento cadastrar(Lancamento lancamento) {
        return repository.save(lancamento);
    }

    @Override
    public Lancamento atualizar(Long id, Lancamento lancamento) {
        lancamento = this.listarPorId(id);
        return repository.save(lancamento);
    }

    @Override
    public void remover(Long id) {
        repository.delete(id);
    }

    @Override
    public void exportarRemessa(List<Lancamento> lancamentos, Convenio convenio, Empresa empresa) throws IOException {

        remessa.exportarRemessa(lancamentos, convenio, empresa);

        convenioService.atualizarSequencial(convenio.getId(), convenio.getSequencial()+1l);
    }

}
