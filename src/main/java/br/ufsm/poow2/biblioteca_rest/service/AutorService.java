package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.repository.AutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {


    @Autowired
    AutorRepo autorRepo;

    public void criarAutor(Autor autor){
        System.out.println(autor.getDataNascAutor() + " é uma " + autor.getDataNascAutor().getClass());
        autorRepo.save(autor);
    }

    public List<Autor> listarAutor(){
        return autorRepo.findAll();
    }

    public void editarAutor(Integer idAutor, Autor editarAutor){
        Optional<Autor> autor = autorRepo.findById(idAutor);
        autor.get().setNomeAutor(editarAutor.getNomeAutor());
        autor.get().setDescricaoAutor(editarAutor.getDescricaoAutor());
        autor.get().setDataMorteAutor(editarAutor.getDataMorteAutor());
        autor.get().setUrlFotoAutor(editarAutor.getUrlFotoAutor());
        autor.get().setDataNascAutor(editarAutor.getDataNascAutor());
        autorRepo.save(autor.get());
    }


    public boolean findById(Integer idAutor) {
        return autorRepo.findById(idAutor).isPresent();
    }

}
