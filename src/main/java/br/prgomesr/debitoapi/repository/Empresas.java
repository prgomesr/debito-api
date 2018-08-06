package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Empresas extends JpaRepository<Empresa, Long> {
}
