package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Genero;
import br.ufsm.poow2.biblioteca_rest.repository.GeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    GeneroRepo generoRepo;

    public ResponseEntity<ApiResponse> criarGenero(Genero genero){
        String nomeGenero = genero.getNomeGenero();
        if (nomeGenero.matches("^[a-zA-ZÀ-ÿ\\s]+$")) {
            if (findGeneroByNomeGenero(nomeGenero) == null){
                generoRepo.save(genero);
                return new ResponseEntity<>(new ApiResponse(true, "Nova categoria criada com sucesso!"), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo gênero! Gênero já existente"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo gênero! Caracteres inválidos."), HttpStatus.BAD_REQUEST);
        }
    }

    public List<Genero> listarGenero(){
        return generoRepo.findAll();
    }

    public ResponseEntity<ApiResponse> editarGenero(Integer generoId, Genero editarGenero) {
        Optional<Genero> optionalGenero = generoRepo.findById(generoId);
        if (optionalGenero.isPresent()) {
            Genero genero = optionalGenero.get();
            genero.update(editarGenero);
            generoRepo.save(genero);
            return new ResponseEntity<>(new ApiResponse(true, "O genero foi editado com sucesso!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "O genero não foi encontrado ou não existe!"), HttpStatus.NOT_FOUND);
    }

    public boolean findById(Integer generoId) {
        return generoRepo.findById(generoId).isPresent();
    }
    public void deletarGenero(Integer generoId) {
        generoRepo.deleteById(generoId);
    }

    public Genero findGeneroByNomeGenero(String nomeGenero){
        return generoRepo.findGeneroByNomeGenero(nomeGenero);
    }

}
