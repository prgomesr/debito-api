package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Convenio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Convenios extends JpaRepository<Convenio, Long> {
}
