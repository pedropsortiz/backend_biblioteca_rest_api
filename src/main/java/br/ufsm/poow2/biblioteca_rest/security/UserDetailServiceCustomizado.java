package br.ufsm.poow2.biblioteca_rest.security;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
import br.ufsm.poow2.biblioteca_rest.service.UsuarioService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = new UsuarioService().getUsuarioByEmail(username);
//        Usuario usuario = new UsuarioDao().getUsuario(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuário ou senha inválidos");
        }else{
            UserDetails user = User.withUsername(usuario.getEmailUsuario())
                    .password(usuario.getSenhaUsuario())
                    .authorities(usuario.getPermissaoUsuario()).build();
            return user;
        }
    }
}
