package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/genero")
public class GenreController {

   @Autowired
   GenreService genreService;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> createGender(@RequestBody Genre genre) {
        // Obtém o nome do gênero a partir do objeto de requisição
        String genderName = genre.getName();

        // Verifica se o nome do gênero é válido
        if (!isValidGenderName(genderName)) {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo gênero! Caracteres inválidos."), HttpStatus.BAD_REQUEST);
        }
        // Verifica se o gênero já existe
        if (genreService.findGenreByName(genderName) != null) {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo gênero! Gênero já existente"), HttpStatus.BAD_REQUEST);
        }

        // Cria o gênero e retorna a resposta
        return genreService.addGenre(genre);
    }

    // Função privada para validar o nome do gênero
    private boolean isValidGenderName(String genderName) {
        // Verifica se o nome do gênero consiste apenas em letras e espaços
        return genderName.matches("^[a-zA-ZÀ-ÿ\\s]+$");
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Genre>> listarGenero(){
        try {
            List<Genre> generos = genreService.findAllGenres();
            if (generos.isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(generos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/editar/{generoId}")
    public ResponseEntity<ApiResponse> editarGenero(@PathVariable("generoId") Integer generoId, @RequestBody Genre genre){
        if (generoId == null || genre == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao editar gênero! Dados inválidos."), HttpStatus.BAD_REQUEST);
        }
        return genreService.updateGenre(generoId, genre);
    }
    @PostMapping("/deletar/{generoId}")
    public ResponseEntity<ApiResponse> deletarGenero(@PathVariable("generoId") Integer generoId){
        if (generoId == null || generoId <= 0) {
            return new ResponseEntity<>(new ApiResponse(false, "O ID do gênero é inválido!"), HttpStatus.BAD_REQUEST);
        }
        return genreService.deleteGenreById(generoId);
    }
}
