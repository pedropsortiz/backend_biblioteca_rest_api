package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Genre;
import br.ufsm.poow2.biblioteca_rest.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    private static final Pattern GENRE_NAME_PATTERN = Pattern.compile("^[a-zA-ZÀ-ÿ\\-\\s]{3,100}$");

    public ResponseEntity<ApiResponse> addGenre(Genre genre){
        ResponseEntity<ApiResponse> response;

        boolean isValidGenreName = isValidGenderName(genre.getName());
        boolean doesGenreAlredyExists = genreRepository.findGenreByName(genre.getName()) != null;

        if (!doesGenreAlredyExists)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao criar novo gênero! Caracteres inválidos."));
        }
        else if (!isValidGenreName)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao criar novo gênero! Gênero já existente"));
        }
        else
        {
            try {
                genreRepository.save(genre);
                response = ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Novo gênero criado com sucesso!"));
            } catch (Exception e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }

    public List<Genre> findAllGenres(){
        return genreRepository.findAll();
    }

    public ResponseEntity<ApiResponse> updateGenre(Integer id, Genre editarGenero) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        ResponseEntity<ApiResponse> response;

        boolean isValidGenreName = isValidGenderName(editarGenero.getName());
        boolean doesGenreExists = genreOptional != null;
        boolean doesGenreAlredyExists = genreRepository.findGenreByName(editarGenero.getName()) != null;

        if (!doesGenreExists)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao editar gênero! Gênero inválido"));
        }
        else if (!doesGenreAlredyExists)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao editar gênero! Caracteres inválidos."));
        }
        else if (!isValidGenreName)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao editar gênero! o gênero digitado já existe"));
        }
        else
        {
            try {
                Genre genre = genreOptional.get();
                genre.update(editarGenero);
                genreRepository.save(genre);
                response = ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "O gênero foi editado com sucesso!"));
            } catch (Exception e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteGenreById(Integer id) {
        Optional<Genre> genre = genreRepository.findById(id);
        ResponseEntity<ApiResponse> response;

        boolean doesGenreExists = genre.isPresent();

        if (!doesGenreExists)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao deletar gênero! Gênero inválido"));
        }
        else
        {
            try {
                genreRepository.deleteById(id);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(false, "Gênero deletado com sucesso."));
            } catch (Exception e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }

        return response;
    }

    private boolean isValidGenderName(String genderName) {
        // Verifica se o nome do gênero corresponde à expressão regular
        return GENRE_NAME_PATTERN.matcher(genderName).matches();
    }


}
