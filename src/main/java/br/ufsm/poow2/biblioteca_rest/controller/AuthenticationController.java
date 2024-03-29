package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.UserRepository;
import br.ufsm.poow2.biblioteca_rest.security.JWTUtil;
import br.ufsm.poow2.biblioteca_rest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> autenticacao(@RequestBody User user){
        User userCredentials = userRepository.findUserByEmail(user.getEmail());
        if (user == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Email ou senha não podem ser nulos."));
        }
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
                String token = new JWTUtil().geraToken(userCredentials);
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Autenticação bem sucedida!", userCredentials, token));
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
