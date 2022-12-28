package br.ufsm.poow2.biblioteca_rest.security;

import br.ufsm.poow2.biblioteca_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
        br.ufsm.poow2.biblioteca_rest.model.User user;
        try {
            user = userService.findUserByEmail(emailUsuario);
        } catch (UsernameNotFoundException ex) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }
        UserDetails userDetails = User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getPermission())
                .build();
        return userDetails;

    }
}