package br.prgomesr.debitoapi.service;

import br.prgomesr.debitoapi.model.Cliente;
import br.prgomesr.debitoapi.model.Convenio;
import br.prgomesr.debitoapi.model.Empresa;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.Lancamentos;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessa;
import br.prgomesr.debitoapi.util.remessa.bb.GerarRemessaInclusao;
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
    private GerarRemessaInclusao remessaInclusao;

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

       // remessa.exportarRemessa(lancamentos, convenio, empresa);
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Mario Santana", "15279", "4018", "4018000015279"));
        remessaInclusao.exportarRemessa(clientes, convenio, empresa);

        convenioService.atualizarSequencial(convenio.getId(), convenio.getSequencial()+1l);
    }

}
