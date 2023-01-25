package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarUser(@RequestBody User user){ return userService.addUser(user); }

    @GetMapping("/listar")
    public List<User> listarUser(){
        return userService.findAllUsers();
    }

    @PostMapping("/editar/{idUser}")
    public ResponseEntity<ApiResponse> editarUser(@PathVariable("idUser") Integer idUser, @RequestBody User user){ return userService.updateUser(idUser, user); }

    @PostMapping("/deletar/{idUser}")
    public ResponseEntity<ApiResponse> deletarUser(@PathVariable("idUser") Integer idUser){
        return userService.deleteUserById(idUser);
    }

}
