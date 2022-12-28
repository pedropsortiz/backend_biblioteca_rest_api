package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.BookDto;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void createBook(BookDto dto, Author author) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setDescription(dto.getDescription());
        book.setTotalQuantity(dto.getTotalQuantity());
        book.setCoverUrl(dto.getCoverUrl());
        book.setInUseQuantity(dto.getInUseQuantity());
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public BookDto mapToBookDto(Book book) {
        BookDto dto = new BookDto();
        dto.setName(book.getName());
        dto.setDescription(book.getDescription());
        dto.setTotalQuantity(book.getTotalQuantity());
        dto.setCoverUrl(book.getCoverUrl());
        dto.setInUseQuantity(book.getInUseQuantity());
        dto.setAuthorId(book.getAuthor().getId());
        dto.setId(book.getId());
        return dto;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        List<BookDto> dtos = new ArrayList<>();
        for (Book book : books) {
            dtos.add(mapToBookDto(book));
        }
        return dtos;
    }

    public void updateBook(BookDto dto, Integer id) throws Exception {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (!bookOptional.isPresent()) {
            throw new Exception("The book does not exist!");
        }
        Book book = bookOptional.get();
        book.update(dto);
        bookRepository.save(book);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}