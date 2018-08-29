package br.prgomesr.debitoapi.repository.queries;

import br.prgomesr.debitoapi.dto.LancamentoNaoRecebidoEstatisticaCliente;
import br.prgomesr.debitoapi.dto.LancamentoRecebidoEstatisticaCliente;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface LancamentosQuery {

    public List<Lancamento> filtrar(LancamentoFilter filter);

    public List<LancamentoProjection> resumir(LancamentoFilter filter);

    public List<LancamentoRecebidoEstatisticaCliente> recebidoPorCliente(LancamentoFilter filter);
    public List<LancamentoNaoRecebidoEstatisticaCliente> naoRecebidoPorCliente(LancamentoFilter filter);

    public Page<LancamentoProjection> resumirComPaginacao(LancamentoFilter filter, Pageable pageable);

}
