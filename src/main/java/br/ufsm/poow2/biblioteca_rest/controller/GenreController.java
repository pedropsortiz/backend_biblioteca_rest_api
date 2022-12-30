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
@RequestMapping("/genre")
public class GenreController {

   @Autowired
   GenreService genreService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addGenre(@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }


    @GetMapping("/list")
    public ResponseEntity<List<Genre>> listGenres(){
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

    @PostMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editarGenero(@PathVariable("id") Integer id, @RequestBody Genre genre){
        return genreService.updateGenre(id, genre);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deletarGenero(@PathVariable("id") Integer id){
        if (id == null || id <= 0) {
            return new ResponseEntity<>(new ApiResponse(false, "O ID do gênero é inválido!"), HttpStatus.BAD_REQUEST);
        }
        return genreService.deleteGenreById(id);
    }
}
