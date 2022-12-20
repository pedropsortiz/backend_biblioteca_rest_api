package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    AutorService autorService;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarAutor(@RequestBody Autor autor){
        autorService.criarAutor(autor);
        return new ResponseEntity<>(new ApiResponse(true, "Novo autor criado com sucesso!"), HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public List<Autor> listarAutor(){
        return autorService.listarAutor();
    }

    @PostMapping("/editar/{idAutor}")
    public ResponseEntity<ApiResponse> editarAutor(@PathVariable("idAutor") Integer idAutor, @RequestBody Autor autor){
        if (!autorService.findById(idAutor)){
            return new ResponseEntity<>(new ApiResponse(false, "O autor não existe ou não foi encontrado!"), HttpStatus.OK);
        }
        autorService.editarAutor(idAutor, autor);
        return new ResponseEntity<>(new ApiResponse(true, "O autor foi editado com sucesso!"), HttpStatus.OK);
    }

}
