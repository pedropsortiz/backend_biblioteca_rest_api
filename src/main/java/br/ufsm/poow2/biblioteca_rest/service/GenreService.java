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

    public ResponseEntity<ApiResponse> addGenre(Genre genre){
        try {
            genreRepository.save(genre);
            return new ResponseEntity<>(new ApiResponse(true, "Nova categoria criada com sucesso!"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo gênero!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Genre> findAllGenres(){
        return genreRepository.findAll();
    }

    public ResponseEntity<ApiResponse> updateGenre(Integer id, Genre editarGenero) {
        Optional<Genre> optionalGenero = genreRepository.findById(id);
        if (!optionalGenero.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "O gênero não foi encontrado ou não existe!"), HttpStatus.NOT_FOUND);
        }
        try {
            Genre genre = optionalGenero.get();
            genre.update(editarGenero);
            genreRepository.save(genre);
            return new ResponseEntity<>(new ApiResponse(true, "O gênero foi editado com sucesso!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao editar gênero!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ApiResponse> deleteGenreById(Integer id) {
        Optional<Genre> genre = genreRepository.findById(id);
        if (!genre.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "O gênero não existe ou não foi encontrado!"), HttpStatus.BAD_REQUEST);
        }

        try {
            genreRepository.deleteById(id);
            return new ResponseEntity<>(new ApiResponse(true, "O gênero foi deletado com sucesso!"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, "Erro ao excluir gênero!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Genre findGenreByName(String nomeGenero){
        return genreRepository.findGenreByName(nomeGenero);
    }

}
