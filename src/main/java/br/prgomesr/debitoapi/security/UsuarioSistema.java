package br.prgomesr.debitoapi.security;

import br.prgomesr.debitoapi.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UsuarioSistema extends User {

    private Usuario usuario;

    public UsuarioSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
        super(usuario.getNome(), usuario.getSenha(), authorities);
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
