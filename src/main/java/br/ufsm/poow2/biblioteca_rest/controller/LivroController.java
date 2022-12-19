package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.model.Livro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {

//    @GetMapping("/livros")
//    public ArrayList<Livro> getLivros(){
//        return new LivroDao().getLivros();
//    }

    @GetMapping
    public Livro getLivro(){
        return null;
//        return new LivroDao().getLivro();
    }

}