package br.prgomesr.debitoapi.repository;

import br.prgomesr.debitoapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Usuarios extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByNome(String nome);
}
