package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Lancamentos extends JpaRepository<Lancamento, Long> {
}
