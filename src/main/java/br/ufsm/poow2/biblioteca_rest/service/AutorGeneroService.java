package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.AutorGeneroDto;
import br.ufsm.poow2.biblioteca_rest.model.*;
import br.ufsm.poow2.biblioteca_rest.repository.AutorGeneroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutorGeneroService {

    @Autowired
    AutorGeneroRepo autorGeneroRepo;

    public void criarAutorGenero(Autor autor, Genero genero) {
        AutorGenero autorGenero = new AutorGenero();
        autorGenero.setGenero(genero);
        autorGenero.setAutor(autor);
        autorGeneroRepo.save(autorGenero);
    }

    public AutorGeneroDto getAutorGenero(AutorGenero autorGenero){
        AutorGeneroDto autorGeneroDto = new AutorGeneroDto();
        autorGeneroDto.setIdAutorGenero(autorGenero.getIdAutorGenero());
        autorGeneroDto.setIdGenero(autorGenero.getGenero().getIdGenero());
        autorGeneroDto.setIdAutor(autorGenero.getAutor().getIdAutor());
        return autorGeneroDto;
    }

    public List<AutorGeneroDto> getAllAutorGenero() {
        List<AutorGenero> autorGeneroList = autorGeneroRepo.findAll();

        List<AutorGeneroDto> autorGeneroDtoList = new ArrayList<>();
        for (AutorGenero autorGenero: autorGeneroList
        ) {
            autorGeneroDtoList.add(getAutorGenero(autorGenero));
        }
        return autorGeneroDtoList;
    }
    public boolean findById(Integer idAutorGenero) {
        return autorGeneroRepo.findById(idAutorGenero).isPresent();
    }

}
