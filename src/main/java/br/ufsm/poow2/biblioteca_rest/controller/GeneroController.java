package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.model.Genero;
import br.ufsm.poow2.biblioteca_rest.repository.GeneroRepo;
import br.ufsm.poow2.biblioteca_rest.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

   @Autowired
   GeneroService generoService;

   @PostMapping("/criar")
   public String criarGenero(@RequestBody Genero genero){
        generoService.criarGenero(genero);
        return "success";
   }

    @GetMapping("/listar")
    public List<Genero> listarGenero(){
        return generoService.listarGenero();
    }

}
