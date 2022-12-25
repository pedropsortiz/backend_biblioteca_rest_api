package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.security.JWTUtil;
import br.ufsm.poow2.biblioteca_rest.service.UsuarioService;
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

    final
    UsuarioService usuarioService;

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody Usuario usuario){
        System.out.println("Email: " + usuario.getEmailUsuario() + "\nSenha: " + usuario.getSenhaUsuario());
        try{
            final Authentication authentication = this.authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(usuario.getEmailUsuario(),
                                    usuario.getSenhaUsuario()
                            )
                    );

            if (authentication.isAuthenticated()){

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Gerando TOCKEN de autenticação");
                String token = new JWTUtil().geraToken(usuario);

                usuarioService.atualizarTokenJWT(usuario, token);

                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
        }catch(Exception E){
            E.printStackTrace();
            return new ResponseEntity<>(
                    "Usuário ou senha incorretos",
                    HttpStatus.BAD_REQUEST
            );
        }

        return null;
    }

}
