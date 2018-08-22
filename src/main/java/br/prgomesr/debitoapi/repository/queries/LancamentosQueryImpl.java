package br.prgomesr.debitoapi.repository.queries;

import br.prgomesr.debitoapi.model.Cliente_;
import br.prgomesr.debitoapi.model.Convenio_;
import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.model.Lancamento_;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
import br.prgomesr.debitoapi.repository.projection.LancamentoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentosQueryImpl implements LancamentosQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = builder.createQuery(Lancamento.class);

        //where
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

        //criar os filtros
        Predicate[] predicates = criarFiltros(filter, builder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public List<LancamentoProjection> resumir(LancamentoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<LancamentoProjection> criteria = builder.createQuery(LancamentoProjection.class);

        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(LancamentoProjection.class,
                root.get(Lancamento_.id), root.get(Lancamento_.convenio).get(Convenio_.numero),
                root.get(Lancamento_.cliente).get(Cliente_.nome),
                root.get(Lancamento_.valor), root.get(Lancamento_.valorPago), root.get(Lancamento_.vencimento),
                root.get(Lancamento_.pagamento), root.get(Lancamento_.situacao),
                root.get(Lancamento_.lote)));

        //criar os filtros
        Predicate[] predicates = criarFiltros(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<LancamentoProjection> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public Page<LancamentoProjection> resumirComPaginacao(LancamentoFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<LancamentoProjection> criteria = builder.createQuery(LancamentoProjection.class);

        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(LancamentoProjection.class,
                root.get(Lancamento_.id), root.get(Lancamento_.convenio).get(Convenio_.numero),
                root.get(Lancamento_.cliente).get(Cliente_.nome),
                root.get(Lancamento_.valor), root.get(Lancamento_.valorPago), root.get(Lancamento_.vencimento),
                root.get(Lancamento_.pagamento), root.get(Lancamento_.situacao),
                root.get(Lancamento_.lote)));

        //criar os filtros
        Predicate[] predicates = criarFiltros(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<LancamentoProjection> query = manager.createQuery(criteria);

        addRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    private Long total(LancamentoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);

        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

        Predicate[] predicates = criarFiltros(filter, builder, root);
        criteriaQuery.where(predicates);

        criteriaQuery.select(builder.count(root));
        return manager.createQuery(criteriaQuery).getSingleResult();
    }

    private void addRestricoesDePaginacao(TypedQuery<LancamentoProjection> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Predicate[] criarFiltros(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(filter.getConvenio())) {
            predicates.add(builder.equal(root.get(Lancamento_.convenio).get(Convenio_.id),
                    filter.getConvenio()));
        }
        if (filter.getVencimento() != null) {
            predicates.add(builder.equal(root.get(Lancamento_.vencimento),
                    filter.getVencimento()));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
