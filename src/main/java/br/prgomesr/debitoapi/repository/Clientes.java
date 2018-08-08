package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface Clientes extends JpaRepository<Cliente, Long> {
}
