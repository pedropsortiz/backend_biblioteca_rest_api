package br.ufsm.poow2.biblioteca_rest.Security;

import br.ufsm.poow2.biblioteca_rest.Dao.UsuarioDao;
import br.ufsm.poow2.biblioteca_rest.Model.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceCustomizado implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = new UsuarioDao().getUsuario(username);
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
