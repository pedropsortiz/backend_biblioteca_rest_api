package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.BookGenreDto;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.BookGenre;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.repository.BookGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookGenreService {

    @Autowired
    BookGenreRepository bookGenreRepository;

    public void addBookGenre(Book book, Genre genre) {
        BookGenre bookGenre = new BookGenre();
        bookGenre.setGenre(genre);
        bookGenre.setBook(book);
        bookGenreRepository.save(bookGenre);
    }

    public BookGenreDto mapToBookGenreDto(BookGenre bookGenre) {
        BookGenreDto dto = new BookGenreDto();
        dto.setId(bookGenre.getId());
        dto.setGenreId(bookGenre.getGenre().getId());
        dto.setBookId(bookGenre.getBook().getId());
        return dto;
    }

    public List<BookGenreDto> findAllBookGenres() {
        List<BookGenre> bookGenres = bookGenreRepository.findAll();
        return bookGenres.stream()
                .map(this::mapToBookGenreDto)
                .collect(Collectors.toList());
    }

}
