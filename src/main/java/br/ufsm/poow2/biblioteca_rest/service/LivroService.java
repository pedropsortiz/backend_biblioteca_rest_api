package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.LivroDto;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.repository.LivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    LivroRepo livroRepo;

    public void criarLivro(LivroDto livroDto, Autor autor) {
        Livro livro = new Livro();
        livro.setNomeLivro(livroDto.getNomeLivro());
        livro.setDescricaoLivro(livroDto.getDescricaoLivro());
        livro.setQntdTotalLivro(livroDto.getQntdTotalLivro());
        livro.setUrlCapaLivro(livroDto.getUrlCapaLivro());
        livro.setQntdEmUsoLivro(livroDto.getQntdEmUsoLivro());
        livro.setAutor(autor);
        livroRepo.save(livro);
    }

    public LivroDto getLivroDto(Livro livro){
        LivroDto livroDto = new LivroDto();
        livroDto.setNomeLivro(livro.getNomeLivro());
        livroDto.setDescricaoLivro(livro.getDescricaoLivro());
        livroDto.setQntdTotalLivro(livro.getQntdTotalLivro());
        livroDto.setUrlCapaLivro(livro.getUrlCapaLivro());
        livroDto.setQntdEmUsoLivro(livro.getQntdEmUsoLivro());
        livroDto.setIdAutor(livro.getAutor().getIdAutor());
        livroDto.setIdLivro(livro.getIdLivro());
        return livroDto;
    }

    public List<LivroDto> getAllLivros() {
        List<Livro> livroList = livroRepo.findAll();

        List<LivroDto> livroDtos = new ArrayList<>();
        for (Livro livro: livroList
             ) {
            livroDtos.add(getLivroDto(livro));
        }
        return livroDtos;
    }

    public void editarLivro(LivroDto livroDto, Integer idLivro) throws Exception {
        Optional<Livro> livroRepoById = livroRepo.findById(idLivro);
        if (!livroRepoById.isPresent()){
            throw new Exception("O livro não existe!");
        }
        Livro livro = livroRepoById.get();
        livro.setNomeLivro(livroDto.getNomeLivro());
        livro.setDescricaoLivro(livroDto.getDescricaoLivro());
        livro.setQntdTotalLivro(livroDto.getQntdTotalLivro());
        livro.setUrlCapaLivro(livroDto.getUrlCapaLivro());
        livro.setQntdEmUsoLivro(livroDto.getQntdEmUsoLivro());
        livroRepo.save(livro);
    }
}
