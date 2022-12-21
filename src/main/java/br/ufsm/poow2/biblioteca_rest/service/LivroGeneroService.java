package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.LivroGeneroDto;
import br.ufsm.poow2.biblioteca_rest.model.*;
import br.ufsm.poow2.biblioteca_rest.repository.LivroGeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroGeneroService {

    @Autowired
    LivroGeneroRepo livroGeneroRepo;

    public void criarLivroGenero(Livro livro, Genero genero) {
        LivroGenero livroGenero = new LivroGenero();
        livroGenero.setGenero(genero);
        livroGenero.setLivro(livro);
        livroGeneroRepo.save(livroGenero);
    }

    public LivroGeneroDto getLivroGenero(LivroGenero livroGenero){
        LivroGeneroDto livroGeneroDto = new LivroGeneroDto();
        livroGeneroDto.setIdLivroGenero(livroGenero.getIdLivroGenero());
        livroGeneroDto.setIdGenero(livroGenero.getGenero().getIdGenero());
        livroGeneroDto.setIdLivro(livroGenero.getLivro().getIdLivro());
        return livroGeneroDto;
    }

    public List<LivroGeneroDto> getAllLivroGenero() {
        List<LivroGenero> livroGeneroList = livroGeneroRepo.findAll();

        List<LivroGeneroDto> livroGeneroDtosList = new ArrayList<>();
        for (LivroGenero livroGenero: livroGeneroList
        ) {
            livroGeneroDtosList.add(getLivroGenero(livroGenero));
        }
        return livroGeneroDtosList;
    }

    public boolean findById(Integer idLivroGenero) {
        return livroGeneroRepo.findById(idLivroGenero).isPresent();
    }

}
