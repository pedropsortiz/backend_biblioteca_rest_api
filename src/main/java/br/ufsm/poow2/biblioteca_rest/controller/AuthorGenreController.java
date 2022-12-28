package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.AuthorGenreDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.repository.AuthorRepository;
import br.ufsm.poow2.biblioteca_rest.repository.GenreRepository;
import br.ufsm.poow2.biblioteca_rest.service.AuthorGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor_genero")
public class AuthorGenreController {

    @Autowired
    AuthorGenreService authorGenreService;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarAuthorGenre(@RequestBody AuthorGenreDto authorGenreDto){
        Optional<Author> optionalAuthor = authorRepository.findById(authorGenreDto.getAuthorId());
        Optional<Genre> optionalGenre = genreRepository.findById(authorGenreDto.getGenreId());
        if (!optionalAuthor.isPresent() || !optionalGenre.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        authorGenreService.addAuthorGender(optionalAuthor.get(), optionalGenre.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo autor atribuído a genero com sucesso!"), HttpStatus.CREATED);
    }
    @GetMapping("/")
    public ResponseEntity<List<AuthorGenreDto>> listarAuthorGenre(){
        List<AuthorGenreDto> authorGenreDtoList = authorGenreService.listAllAuthorGenderDtos();
        return new ResponseEntity<>(authorGenreDtoList, HttpStatus.OK);
    }

}
