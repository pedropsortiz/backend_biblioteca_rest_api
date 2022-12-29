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

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @GetMapping("/list")
    public List<Author> listAuthors(){
        return authorService.listAllAuthors();
    }

    @PostMapping("/listOne/{id}")
    public ResponseEntity<ApiResponse> listOneAuthor(@PathVariable("id") Integer id){
        return authorService.getAuthorById(id);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editAuthor(@PathVariable("id") Integer id, @RequestBody Author author){
        return authorService.updateAuthor(id, author);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAuthor(@PathVariable("id") Integer id){
        return authorService.deleteAuthorById(id);
    }

}
