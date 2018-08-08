package br.prgomesr.debitoapi.repository.queries;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;

import java.util.List;

public interface LancamentosQuery {

    public List<Lancamento> filtrar(LancamentoFilter filter);

}
