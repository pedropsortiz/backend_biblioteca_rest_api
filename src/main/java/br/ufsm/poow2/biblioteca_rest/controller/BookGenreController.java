package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.BookGenreDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.repository.GenreRepository;
import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import br.ufsm.poow2.biblioteca_rest.service.BookGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro_genero")
public class BookGenreController {

    @Autowired
    BookGenreService bookGenreService;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    GenreRepository genreRepository;

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarBookGenre(@RequestBody BookGenreDto bookGenreDto){
        Optional<Book> optionalBook = bookRepository.findById(bookGenreDto.getBookId());
        Optional<Genre> optionalGenre = genreRepository.findById(bookGenreDto.getGenreId());
        if (!optionalBook.isPresent() || !optionalGenre.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O empréstimo selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        bookGenreService.addBookGenre(optionalBook.get(), optionalGenre.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo livro atribuído a genero com sucesso!"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookGenreDto>> listarBookGenre(){
        List<BookGenreDto> bookGenreDtoList = bookGenreService.listBookGenreDtos();
        return new ResponseEntity<>(bookGenreDtoList, HttpStatus.OK);
    }

}
