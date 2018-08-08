package br.prgomesr.debitoapi.repository.queries;

import br.prgomesr.debitoapi.model.Lancamento;
import br.prgomesr.debitoapi.repository.filter.LancamentoFilter;
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

    private Predicate[] criarFiltros(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(filter.getLote())) {
            predicates.add(builder.like(
                    builder.lower(root.get("lote")),
                    "%" + filter.getLote().toLowerCase()+ "%"
            ));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
