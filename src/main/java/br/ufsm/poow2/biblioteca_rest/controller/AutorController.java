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
    public ResponseEntity<ApiResponse> criarAutor(@RequestBody Autor autor){ return autorService.criarAutor(autor); }

    @GetMapping("/listar")
    public List<Autor> listarAutor(){
        return autorService.listarAutor();
    }

    @PostMapping("/editar/{idAutor}")
    public ResponseEntity<ApiResponse> editarAutor(@PathVariable("idAutor") Integer idAutor, @RequestBody Autor autor){ return autorService.editarAutor(idAutor, autor); }

    @PostMapping("/deletar/{idAutor}")
    public ResponseEntity<ApiResponse> deletarGenero(@PathVariable("idAutor") Integer idAutor){ return autorService.deletarAutor(idAutor); }

}
