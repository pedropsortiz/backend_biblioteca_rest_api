package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Usuario usuario){
        System.out.println("Login: " + usuario);

        try{
            final Authentication authentication = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmailUsuario(), usuario.getSenhaUsuario()));
            if (authentication.isAuthenticated()){

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Gerando TOCKEN de autenticação");
                String token = new JWTUtil().geraToken(usuario);

                usuario.setTokenUsuario(token);
                usuario.setSenhaUsuario(null);

                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
        }catch(Exception E){
            E.printStackTrace();
            return new ResponseEntity<>(
                    "Usuário ou senha incorretos",
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                "Usuário ou senha incorretos",
                HttpStatus.BAD_REQUEST
        );

    }

}
