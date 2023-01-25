package br.ufsm.poow2.biblioteca_rest.controller;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Book;
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
@CrossOrigin
public class BookController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookController(BookService bookService, AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBook(@RequestBody BookDto bookDto){
        Optional<Author> optionalAuthor = authorRepository.findById(bookDto.getAuthorId());
        return bookService.addBook(bookDto, optionalAuthor.get());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> listBooks(){
        List<Book> allLivros = bookService.findAllBooks();
        return new ResponseEntity<>(allLivros, HttpStatus.OK);
    }

    @PostMapping("/listOne/{id}")
    public ResponseEntity<ApiResponse> listOneBook(@PathVariable("id") Integer id){
        return bookService.getBookById(id);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<ApiResponse> editBook(@PathVariable("id") Integer id, @RequestBody BookDto bookDto) throws Exception {
        return bookService.updateBook(bookDto, id);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteBook(@PathVariable("id") Integer id) {
        return bookService.deleteBookById(id);
    }

}