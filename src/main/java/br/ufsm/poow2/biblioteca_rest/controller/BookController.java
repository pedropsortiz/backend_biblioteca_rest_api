package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.repository.AuthorRepository;
import br.ufsm.poow2.biblioteca_rest.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/criar")
    public ResponseEntity<ApiResponse> criarLivro(@RequestBody BookDto bookDto){
        Optional<Author> optionalAuthor = authorRepository.findById(bookDto.getAuthorId());
        if (!optionalAuthor.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O livro selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        bookService.addBook(bookDto, optionalAuthor.get());
        return new ResponseEntity<>(new ApiResponse(true, "Novo livro criado com sucesso!"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> listarLivro(){
        List<BookDto> allLivros = bookService.findAllBooks();
        return new ResponseEntity<>(allLivros, HttpStatus.OK);
    }

    @PostMapping("/editar/{idLivro}")
    public ResponseEntity<ApiResponse> editarLivro(@PathVariable("idLivro") Integer idLivro, @RequestBody BookDto bookDto) throws Exception {
        Optional<Author> optionalAuthor = authorRepository.findById(bookDto.getAuthorId());
        if (!optionalAuthor.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false, "O livro selecionado não existe!"), HttpStatus.BAD_REQUEST);
        }
        bookService.updateBook(bookDto, idLivro);
        return new ResponseEntity<>(new ApiResponse(true, "Livro editado com sucesso!"), HttpStatus.CREATED);
    }

    @PostMapping("/deletar/{idLivro}")
    public ResponseEntity<ApiResponse> deletarLivro(@PathVariable("idLivro") Integer idLivro) throws Exception {
        bookService.deleteBookById(idLivro);
        return new ResponseEntity<>(new ApiResponse(true, "Livro deletado com sucesso!"), HttpStatus.OK);
    }

}