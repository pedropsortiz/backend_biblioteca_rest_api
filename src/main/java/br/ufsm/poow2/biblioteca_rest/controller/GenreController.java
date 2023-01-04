package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        List<Genre> allGenres = genreService.findAllGenres();
        return new ResponseEntity<>(allGenres, HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> updateGenre(@PathVariable("id") Integer id, @RequestBody Genre genre){
        return genreService.updateGenre(id, genre);
    }

    @PostMapping("/listOne/{id}")
    public ResponseEntity<Genre> listOneGenre(@PathVariable("id") Integer id){
        Genre genre = genreService.findOneGenre(id);
        return new ResponseEntity<>(genre, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delteGenre(@PathVariable("id") Integer id){
        return genreService.deleteGenreById(id);
    }
}
