package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface Convenios extends JpaRepository<Convenio, Long> {
}
