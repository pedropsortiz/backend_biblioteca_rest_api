package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Genero;
import br.ufsm.poow2.biblioteca_rest.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genero")
public class GeneroController {

   @Autowired
   GeneroService generoService;

   @PostMapping("/criar")
   public ResponseEntity<ApiResponse> criarGenero(@RequestBody Genero genero){ return generoService.criarGenero(genero); }

    @GetMapping("/listar")
    public List<Genero> listarGenero(){
        return generoService.listarGenero();
    }

    @PostMapping("/editar/{generoId}")
    public ResponseEntity<ApiResponse> editarGenero(@PathVariable("generoId") Integer generoId, @RequestBody Genero genero){
       if (!generoService.findById(generoId)){
           return new ResponseEntity<>(new ApiResponse(false, "A categoria não existe ou não foi encontrada!"), HttpStatus.OK);
       }
       generoService.editarGenero(generoId, genero);
       return new ResponseEntity<>(new ApiResponse(true, "A categoria foi editada com sucesso!"), HttpStatus.OK);
    }

    @PostMapping("/deletar/{generoId}")
    public ResponseEntity<ApiResponse> deletarGenero(@PathVariable("generoId") Integer generoId){
        if (!generoService.findById(generoId)){
            return new ResponseEntity<>(new ApiResponse(false, "O gênero não existe ou não foi encontrado!"), HttpStatus.OK);
        }
        generoService.deletarGenero(generoId);
        return new ResponseEntity<>(new ApiResponse(true, "O gênero foi deletado com sucesso!"), HttpStatus.OK);
    }

}
