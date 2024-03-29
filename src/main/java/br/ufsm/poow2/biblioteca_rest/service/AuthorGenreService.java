package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.AuthorGenreDto;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.AuthorGenre;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.repository.AuthorGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorGenreService {

    @Autowired
    AuthorGenreRepository authorGenreRepository;

    public void addAuthorGender(Author author, Genre genre) {
        if (author == null || genre == null) {
            throw new IllegalArgumentException("Autor e gênero não podem ser nulos");
        }

        AuthorGenre authorGenre = new AuthorGenre();
        authorGenre.setAuthor(author);
        authorGenre.setGenre(genre);
        authorGenreRepository.save(authorGenre);
    }

    public AuthorGenreDto mapToAuthorGenderDto(AuthorGenre authorGenre) {
        AuthorGenreDto dto = new AuthorGenreDto();
        dto.setId(authorGenre.getId());
        dto.setGenreId(authorGenre.getGenre().getId());
        dto.setAuthorId(authorGenre.getAuthor().getId());
        return dto;
    }

    public List<AuthorGenreDto> listAllAuthorGenderDtos() {
        List<AuthorGenre> authorGenders = authorGenreRepository.findAll();
        return authorGenders.stream()
                .map(this::mapToAuthorGenderDto)
                .collect(Collectors.toList());
    }

}
