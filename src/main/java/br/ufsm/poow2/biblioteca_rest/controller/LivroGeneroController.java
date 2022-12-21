package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.AutorGeneroDto;
import br.ufsm.poow2.biblioteca_rest.DTO.LivroGeneroDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.*;
import br.ufsm.poow2.biblioteca_rest.repository.GeneroRepo;
import br.ufsm.poow2.biblioteca_rest.repository.LivroRepo;
import br.ufsm.poow2.biblioteca_rest.service.AutorGeneroService;
import br.ufsm.poow2.biblioteca_rest.service.AutorService;
import br.ufsm.poow2.biblioteca_rest.service.LivroGeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro_genero")
public class LivroGeneroController {

    @Autowired
    LivroGeneroService livroGeneroService;

    @Autowired
    LivroRepo livroRepo;

    @Autowired
    GeneroRepo generoRepo;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarLivroGenero(@RequestBody LivroGeneroDto livroGeneroDto){
        Optional<Livro> optionalLivro = livroRepo.findById(livroGeneroDto.getIdLivro());
        Optional<Genero> optionalGenero = generoRepo.findById(livroGeneroDto.getIdGenero());
        if (!optionalLivro.isPresent() || !optionalGenero.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        livroGeneroService.criarLivroGenero(optionalLivro.get(), optionalGenero.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo livro atribuído a genero com sucesso!"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<LivroGeneroDto>> listarLivroGenero(){
        List<LivroGeneroDto> livroGeneroDtoList = livroGeneroService.getAllLivroGenero();
        return new ResponseEntity<>(livroGeneroDtoList, HttpStatus.OK);
    }

}
