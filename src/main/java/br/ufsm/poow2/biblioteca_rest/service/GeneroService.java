package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.model.Genero;
import br.ufsm.poow2.biblioteca_rest.repository.GeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
