package br.ufsm.poow2.biblioteca_rest.Controller;

import br.ufsm.poow2.biblioteca_rest.Dao.LivroDao;
import br.ufsm.poow2.biblioteca_rest.Model.Livro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @GetMapping("/livros")
    public ArrayList<Livro> getLivros(){
        return new LivroDao().getLivros();
    }

    @GetMapping
    public Livro getLivro(){
        return new LivroDao().getLivro();
    }

}