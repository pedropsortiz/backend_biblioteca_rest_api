package br.ufsm.poow2.biblioteca_rest.Dao;

import br.ufsm.poow2.biblioteca_rest.Model.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UsuarioDao {

    Usuario pedro = new Usuario(1, "Pedro Ortiz", "pedro.ortiz@gmail.com", new BCryptPasswordEncoder().encode("123"), "usr");
    Usuario jorge = new Usuario(1, "Jorge Amado", "jorge.amado@gmail.com", new BCryptPasswordEncoder().encode("123"), "adm");

    public Usuario getUsuario(String login){
        if (login.equals(pedro.getEmailUsuario())){
            return pedro;
        } else if (login.equals(jorge.getEmailUsuario())) {
            return jorge;
        } else {
            return null;
        }
    }

}