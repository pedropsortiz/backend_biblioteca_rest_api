package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorService authorService;

    public ResponseEntity<ApiResponse> addBook(BookDto dto, Author author) {
        ResponseEntity<ApiResponse> response;

        boolean isNameValid = isNameValid(dto.getName());
        boolean isDescriptionValid = isDescriptionValid(dto.getDescription());
        boolean isAuthorValid = authorService.getAuthorById(dto.getAuthorId()) != null;
        boolean isTotalQuantityValid = isTotalQuantityValid(dto.getTotalQuantity());
        boolean isInUseQuantityValid = isInUseQuantityValid(dto.getInUseQuantity(), dto.getTotalQuantity());

        if (!isNameValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "O título do livro é inválido ou está vazio. O tamanho mínimo é de 3 caracteres."));
        }
        else if (!isDescriptionValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "A descrição do livro é inválida ou tem menos de 10 caracteres."));
        }
        else if (!isAuthorValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "O autor do livro é inválido ou não foi preenchido."));
        }
        else if (!isTotalQuantityValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "A quantidade total do livro é inválida ou é menor que zero."));
        }
        else if (!isInUseQuantityValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "A quantidade em uso do livro é inválida ou é menor que zero ou maior que a quantidade total."));
        }
        else
        {
            try {
                Book book = new Book();
                book.setName(dto.getName());
                book.setDescription(dto.getDescription());
                book.setTotalQuantity(dto.getTotalQuantity());
                book.setCoverUrl(dto.getCoverUrl());
                book.setInUseQuantity(dto.getInUseQuantity());
                book.setAuthor(author);
                bookRepository.save(book);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O livro foi criado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }

    public BookDto mapToBookDto(Book book) {
        BookDto dto = new BookDto();
        BeanUtils.copyProperties(book, dto);
        dto.setAuthorId(book.getAuthor().getId());
        return dto;
    }

    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ApiResponse> updateBook(BookDto dto, Integer id){
        ResponseEntity<ApiResponse> response;
        Book book = bookRepository.findById(id).orElse(null);
        Optional<Author> author = authorService.getOneAuthor(dto.getAuthorId());

        boolean isBookValid = book != null;
        boolean isAuthorValid = author.isPresent();
        boolean isNameValid = isNameValid(dto.getName());
        boolean isDescriptionValid = isDescriptionValid(dto.getDescription());
        boolean isTotalQuantityValid = isTotalQuantityValid(dto.getTotalQuantity());
        boolean isInUseQuantityValid = isInUseQuantityValid(dto.getInUseQuantity(), dto.getTotalQuantity());

        if (!isBookValid)
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "O livro selecionado não existe ou não foi encontrado"));
        }
        else if (!isNameValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "O título do livro é inválido ou está vazio. O tamanho mínimo é de 3 caracteres."));
        }
        else if (!isDescriptionValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "A descrição do livro é inválida ou tem menos de 10 caracteres."));
        }
        else if (!isAuthorValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "O autor do livro é inválido ou não foi preenchido."));
        }
        else if (!isTotalQuantityValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "A quantidade total do livro é inválida ou é menor que zero."));
        }
        else if (!isInUseQuantityValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "A quantidade em uso do livro é inválida ou é menor que zero ou maior que a quantidade total."));
        }
        else
        {
            try {
                book.update(dto, author.get());
                bookRepository.save(book);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O livro foi editado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteBookById(Integer id) {
        ResponseEntity<ApiResponse> response;
        Book bookOptional = bookRepository.findById(id).orElse(null);

        boolean doesBookExists = (bookOptional==null) ? false: true;

        if (!doesBookExists)
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "O livro não existe ou não foi encontrado!"));
        }
        else
        {
            try {
                bookRepository.deleteById(id);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O livro foi excluido com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }

    public boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty() && name.trim().length() >= 3;
    }

    public boolean isDescriptionValid(String description) {
        return description == null || !description.trim().isEmpty() && description.trim().length() >= 10;
    }

    public boolean isTotalQuantityValid(int totalQuantity) {
        return totalQuantity >= 0;
    }

    public boolean isInUseQuantityValid(int inUseQuantity, int totalQuantity) {
        return inUseQuantity >= 0 && inUseQuantity <= totalQuantity;
    }

    public ResponseEntity<ApiResponse> getBookById(Integer id)  {
        Book book = bookRepository.findById(id).orElse(null);
        ResponseEntity<ApiResponse> response;

        boolean doesBookExists = (book == null) ? false : true;

        if (!doesBookExists)
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Livro não encontrado"));
        }
        else
        {
            try {
                Gson gson = new Gson();
                String jsonBook = gson.toJson(book);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, jsonBook));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Erro de acesso a data"));
            }
        }
        return response;
    }
}