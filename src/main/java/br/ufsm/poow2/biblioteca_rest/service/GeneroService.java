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
    public void editarGenero(Integer generoId, Genero editarGenero){
        Optional<Genero> genero = generoRepo.findById(generoId);
        genero.get().setNomeGenero(editarGenero.getNomeGenero());
        generoRepo.save(genero.get());
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
