package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarAuthor(@RequestBody Author author){ return authorService.addAuthor(author); }

    @GetMapping("/listar")
    public List<Author> listarAuthor(){
        return authorService.listAllAuthors();
    }

    @PostMapping("/editar/{idAuthor}")
    public ResponseEntity<ApiResponse> editarAuthor(@PathVariable("idAuthor") Integer idAuthor, @RequestBody Author author){ return authorService.updateAuthor(idAuthor, author); }

    @PostMapping("/deletar/{idAuthor}")
    public ResponseEntity<ApiResponse> deletarGenero(@PathVariable("idAuthor") Integer idAuthor){ return authorService.deleteAuthorById(idAuthor); }

}
