package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.exception.BookException;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.repository.BookGenreRepository;
import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookException bookException;

    private final BookGenreRepository bookGenreRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorService authorService, BookException bookException, BookGenreRepository bookGenreRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookException = bookException;
        this.bookGenreRepository = bookGenreRepository;
    }

    /*
    CRUD
     */

    //Adicionar Livro no banco de dados
    public ResponseEntity<ApiResponse> addBook(BookDto dto, Author author) throws JsonProcessingException {
        ResponseEntity<ApiResponse> response;
        Map<String, String> handleErrors = bookException.handleAddBookErrors(dto, author);

        if (handleErrors.isEmpty()) {
            try {
                Book book = new Book();
                book.update(dto, author);
                bookRepository.save(book);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O livro foi criado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao criar novo livro.", handleErrors)
            );
        }

        return response;
    }

    //Mapear os Livros no banco de dados
    public BookDto mapToBookDto(Book book) {
        BookDto dto = new BookDto();
        BeanUtils.copyProperties(book, dto);
        dto.setAuthorId(book.getAuthor().getId());
        return dto;
    }

    //Exibir os livros pelo banco
    public List<Book> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books) {
            Optional<Author> author = authorService.findById(book.getAuthor().getId());
            book.setAuthor(author.get());
        }
        return books;
    }

    //Atualizar Livro
    public ResponseEntity<ApiResponse> updateBook(BookDto dto, Integer id){
        ResponseEntity<ApiResponse> response;
        Map<String, String> handleErrors = bookException.handleDeleteBookErrors(id);

        Book book = bookRepository.findById(id).orElse(null);
        Optional<Author> author = authorService.getOneAuthor(dto.getAuthorId());

        if (handleErrors.isEmpty()) {
            try {
                book.update(dto, author.get());
                bookRepository.save(book);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O livro foi editado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao atualizar livro.", handleErrors)
            );
        }

        return response;
    }

    //Deletar livro
    public ResponseEntity<ApiResponse> deleteBookById(Integer id) {
        ResponseEntity<ApiResponse> response;
        Map<String, String> handleErrors = bookException.handleDeleteBookErrors(id);

        if (handleErrors.isEmpty()) {
            try {
                bookGenreRepository.deleteByBook(bookRepository.findById(id).get());
                bookRepository.deleteById(id);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O livro foi excluido com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao deletar livro.", handleErrors)
            );
        }

        return response;
    }

    //Exibir um livro pelo ID
    public ResponseEntity<ApiResponse> getBookById(Integer id)  {
        ResponseEntity<ApiResponse> response;
        Book book = bookRepository.findById(id).orElse(null);
        Map<String, String> handleErrors = bookException.handleGetBookErrors(id);

        if (handleErrors.isEmpty()) {
            try {
                Gson gson = new Gson();
                String jsonBook = gson.toJson(book);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, jsonBook));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Erro de acesso a data"));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao buscar livro.", handleErrors)
            );
        }

        return response;
    }

}