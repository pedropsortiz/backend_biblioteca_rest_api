package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> autenticacao(@RequestBody User user){
        try{
            final Authentication authentication = this.authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(user.getEmail(),
                                    user.getPassword()
                            )
                    );

            if (authentication.isAuthenticated()){

                SecurityContextHolder.getContext().setAuthentication(authentication);

                System.out.println("Gerando TOCKEN de autenticação");
                String token = new JWTUtil().geraToken(user);
                userService.updateJwtToken(user.getEmail(), token);
                User userResponse = userService.findUserByEmail(user.getEmail());

                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Autenticação bem sucedida!"));
            }
        }catch(Exception E){
            E.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(
                    false,
                    "Falha ao atualizar usuário.",
                    Map.of("email","Email ou senha estão incorretos.")
                )
            );
        }

        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signUp(@RequestBody User user){
        return userService.addUser(user);
    }

}
