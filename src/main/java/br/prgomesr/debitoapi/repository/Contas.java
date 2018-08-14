package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Contas extends JpaRepository<Conta, Long> {
}
