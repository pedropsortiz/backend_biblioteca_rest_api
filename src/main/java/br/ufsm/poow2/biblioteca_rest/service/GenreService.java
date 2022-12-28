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

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public ResponseEntity<ApiResponse> createGender(Genre genre){
        try {
            genreRepository.save(genre);
            return new ResponseEntity<>(new ApiResponse(true, "Nova categoria criada com sucesso!"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo gênero!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Genre> listarGenero(){
        return genreRepository.findAll();
    }

    public ResponseEntity<ApiResponse> editarGenero(Integer generoId, Genre editarGenero) {
        Optional<Genre> optionalGenero = genreRepository.findById(generoId);
        if (!optionalGenero.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "O gênero não foi encontrado ou não existe!"), HttpStatus.NOT_FOUND);
        }
        try {
            Genre genero = optionalGenero.get();
            genero.update(editarGenero);
            genreRepository.save(genero);
            return new ResponseEntity<>(new ApiResponse(true, "O gênero foi editado com sucesso!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao editar gênero!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean findById(Integer generoId) {
        return genreRepository.findById(generoId).isPresent();
    }

    public ResponseEntity<ApiResponse> deletarGenero(Integer generoId) {
        Optional<Genre> genero = genreRepository.findById(generoId);
        if (!genero.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "O gênero não existe ou não foi encontrado!"), HttpStatus.BAD_REQUEST);
        }

        try {
            genreRepository.deleteById(generoId);
            return new ResponseEntity<>(new ApiResponse(true, "O gênero foi deletado com sucesso!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Erro ao excluir gênero!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Genre findGenderByName(String nomeGenero){
        return genreRepository.findGenreByName(nomeGenero);
    }

}
