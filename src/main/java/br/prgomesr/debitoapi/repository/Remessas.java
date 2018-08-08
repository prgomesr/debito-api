package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Remessa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface Remessas extends JpaRepository<Remessa, Long> {
}
