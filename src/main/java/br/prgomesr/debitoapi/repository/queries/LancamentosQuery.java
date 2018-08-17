package br.prgomesr.debitoapi.repository.queries;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;

import java.util.List;

public interface LancamentosQuery {

    public List<Lancamento> filtrar(LancamentoFilter filter);
    public List<LancamentoProjection> resumir(LancamentoFilter filter);

}
