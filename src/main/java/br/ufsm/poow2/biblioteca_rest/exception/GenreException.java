package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@ControllerAdvice
public class GenreException {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreException(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private static final Pattern GENRE_NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ÿ\\-\\s]{3,100}$");

    public List<String> handleAddGenreErrors(Genre genre) {
        List<String> errors = new ArrayList<>();

        if (!doesGenreExists(genre))
        {
            errors.add("Falha ao criar novo gênero! Caracteres inválidos.");
        }
        if (!isValidGenreName(genre.getName()))
        {
            errors.add("Falha ao criar novo gênero! Gênero já existente");
        }

        return errors;
    }

    public List<String> handleUpdateGenreErrors(Integer id, Genre genre) {
        List<String> errors = new ArrayList<>();
        Genre genreRepo = genreRepository.findById(id).orElse(null);

        if (genreRepo.equals(null)){
            errors.add("Usuário não encontrado ou não existe!");
        }else{
            errors = handleAddGenreErrors(genre);
        }

        return errors;
    }

    public List<String> handleDeleteGenreErrors(Integer id) {
        List<String> errors = new ArrayList<>();

        Genre genreRepo = genreRepository.findById(id).orElse(null);
        if (genreRepo==null)
            errors.add("Falha ao excluir gênero. O gênero selecionado não existe ou não foi encontrado");

        return errors;
    }

    private boolean isValidGenreName(String genderName) {
        // Verifica se o nome do gênero corresponde à expressão regular
        return GENRE_NAME_PATTERN.matcher(genderName).matches();
    }

    private boolean doesGenreExists(Genre genre){
        return genreRepository.findGenreByName(genre.getName()) != null;
    }
}
