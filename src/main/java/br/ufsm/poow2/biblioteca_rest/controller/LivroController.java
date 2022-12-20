package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.LivroDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.repository.AutorRepo;
import br.ufsm.poow2.biblioteca_rest.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    LivroService livroService;

    @Autowired
    AutorRepo autorRepo;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarLivro(@RequestBody LivroDto livroDto){
        Optional<Autor> optionalAutor = autorRepo.findById(livroDto.getIdAutor());
        if (!optionalAutor.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O livro selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        livroService.criarLivro(livroDto, optionalAutor.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo livro criado com sucesso!"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<LivroDto>> listarLivro(){
        List<LivroDto> allLivros = livroService.getAllLivros();
        return new ResponseEntity<>(allLivros, HttpStatus.OK);
    }

    @PostMapping("/editar/{idLivro}")
    public ResponseEntity<ApiResponse> editarLivro(@PathVariable("idLivro") Integer idLivro, @RequestBody LivroDto livroDto) throws Exception {
        Optional<Autor> optionalAutor = autorRepo.findById(livroDto.getIdAutor());
        if (!optionalAutor.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O livro selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        livroService.editarLivro(livroDto, idLivro);
        return new ResponseEntity<>(new ApiResponse(true, "Livro editado com sucesso!"), HttpStatus.CREATED);
    }

}