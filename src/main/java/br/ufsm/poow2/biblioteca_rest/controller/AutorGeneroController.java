package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.AutorGeneroDto;
import br.ufsm.poow2.biblioteca_rest.DTO.LivroDto;
import br.ufsm.poow2.biblioteca_rest.DTO.LivroGeneroDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.model.AutorGenero;
import br.ufsm.poow2.biblioteca_rest.model.Genero;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.repository.AutorRepo;
import br.ufsm.poow2.biblioteca_rest.repository.GeneroRepo;
import br.ufsm.poow2.biblioteca_rest.repository.LivroRepo;
import br.ufsm.poow2.biblioteca_rest.service.AutorGeneroService;
import br.ufsm.poow2.biblioteca_rest.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor_genero")
public class AutorGeneroController {

    @Autowired
    AutorGeneroService autorGeneroService;

    @Autowired
    AutorRepo autorRepo;

    @Autowired
    GeneroRepo generoRepo;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarAutorGenero(@RequestBody AutorGeneroDto autorGeneroDto){
        Optional<Autor> optionalAutor = autorRepo.findById(autorGeneroDto.getIdAutor());
        Optional<Genero> optionalGenero = generoRepo.findById(autorGeneroDto.getIdGenero());
        if (!optionalAutor.isPresent() || !optionalGenero.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        autorGeneroService.criarAutorGenero(optionalAutor.get(), optionalGenero.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo autor atribuído a genero com sucesso!"), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<AutorGeneroDto>> listarAutorGenero(){
        List<AutorGeneroDto> autorGeneroDtoList = autorGeneroService.getAllAutorGenero();
        return new ResponseEntity<>(autorGeneroDtoList, HttpStatus.OK);
    }

}
