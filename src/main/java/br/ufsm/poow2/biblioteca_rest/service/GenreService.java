package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.exception.GenreException;
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

    @Autowired
    GenreException genreException;


    public ResponseEntity<ApiResponse> addGenre(Genre genre){
        ResponseEntity<ApiResponse> response;
        List<String> handleErrors = genreException.handleAddGenreErrors(genre);

        if (handleErrors.isEmpty()){
            try {
                genreRepository.save(genre);
                response = ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Novo gênero criado com sucesso!"));
            } catch (Exception e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao criar novo usuário. " + String.join(" ", handleErrors))
            );
        }
        return response;
    }

    public List<Genre> findAllGenres(){
        return genreRepository.findAll();
    }

    public Genre findOneGenre(int id){
        return genreRepository.findById(id).get();
    }

    public ResponseEntity<ApiResponse> updateGenre(Integer id, Genre updatedGenre) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        List<String> handleErrors = genreException.handleUpdateGenreErrors(id, updatedGenre);
        ResponseEntity<ApiResponse> response;

        if (handleErrors.isEmpty()){
            try {
                Genre genre = genreOptional.get();
                genre.update(updatedGenre);
                genreRepository.save(genre);
                response = ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "O gênero foi editado com sucesso!"));
            } catch (Exception e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao editar gênero. " + String.join(" ", handleErrors))
            );
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteGenreById(Integer id) {
        ResponseEntity<ApiResponse> response;
        List<String> handleErrors = genreException.handleDeleteGenreErrors(id);

        if (handleErrors.isEmpty()){
            try {
                genreRepository.deleteById(id);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(false, "Gênero deletado com sucesso."));
            } catch (Exception e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }else {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao deletar gênero. " + String.join(" ", handleErrors))
            );
        }
        return response;
    }

}
