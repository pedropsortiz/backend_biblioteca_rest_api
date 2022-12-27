package br.ufsm.poow2.biblioteca_rest.security;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
import br.ufsm.poow2.biblioteca_rest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
        Usuario usuario;
        try {
            usuario = usuarioService.getUsuarioByEmail(emailUsuario);
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
        UserDetails user = User.withUsername(usuario.getEmailUsuario())
                .password(usuario.getSenhaUsuario())
                .authorities(usuario.getPermissaoUsuario())
                .build();

        return user;

    }
}
