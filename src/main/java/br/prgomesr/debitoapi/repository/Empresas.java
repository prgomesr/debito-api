package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface Empresas extends JpaRepository<Empresa, Long> {
}
