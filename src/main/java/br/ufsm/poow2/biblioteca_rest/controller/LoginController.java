package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.security.JWTUtil;
import br.ufsm.poow2.biblioteca_rest.service.UserService;
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
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Object> autenticacao(@RequestBody User user){
        try{
            final Authentication authentication = this.authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(user.getEmail(),
                                    user.getPassword()
                            )
                    );

            if (authentication.isAuthenticated()){

                SecurityContextHolder.getContext().setAuthentication(authentication);
                User userResponse = userService.findUserByEmail(user.getEmail());

                System.out.println("Gerando TOCKEN de autenticação");
                String token = new JWTUtil().geraToken(userResponse);
                userService.updateJwtToken(user.getEmail(), token);

                return new ResponseEntity<>(userResponse, HttpStatus.OK);
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
