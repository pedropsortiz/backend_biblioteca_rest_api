package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.model.Genero;
import br.ufsm.poow2.biblioteca_rest.repository.GeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    GeneroRepo generoRepo;

    public void criarGenero(Genero genero){
        generoRepo.save(genero);
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
}
