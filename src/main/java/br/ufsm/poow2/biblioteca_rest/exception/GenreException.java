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
import java.util.Map;
import java.util.regex.Pattern;

@ControllerAdvice
public class GenreException {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreException(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    private static final Pattern GENRE_NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ÿ\\-\\s]{3,100}$");

    public Map<String, String> handleAddGenreErrors(Genre genre) {
        ApiResponse apiResponse = new ApiResponse();

        if (doesGenreExists(genre))
        {
            apiResponse.addError("name", "Falha ao criar novo gênero! Gênero já existente");
        }
        if (!(isValidGenreName(genre.getName())))
        {
            apiResponse.addError("name", "Falha ao criar novo gênero! Caracteres inválidos.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String>handleUpdateGenreErrors(Integer id, Genre genre) {
        ApiResponse apiResponse = new ApiResponse();
        Genre genreRepo = genreRepository.findById(id).orElse(null);

        if (genreRepo.equals(null)){
            apiResponse.addError("name", "Usuário não encontrado ou não existe!");
        }else{
            apiResponse.setErrors(handleAddGenreErrors(genre));
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleDeleteGenreErrors(Integer id) {
        ApiResponse apiResponse = new ApiResponse();

        Genre genreRepo = genreRepository.findById(id).orElse(null);
        if (genreRepo==null)
            apiResponse.addError("name", "Falha ao excluir gênero. O gênero selecionado não existe ou não foi encontrado");

        return apiResponse.getErrors();
    }

    private boolean isValidGenreName(String genderName) {
        // Verifica se o nome do gênero corresponde à expressão regular
        return GENRE_NAME_PATTERN.matcher(genderName).matches();
    }

    private boolean doesGenreExists(Genre genre){
        System.out.println(genreRepository.findGenreByName(genre.getName()));
        return genreRepository.findGenreByName(genre.getName()) != null;
    }
}
