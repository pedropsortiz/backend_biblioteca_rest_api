package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.LivroDto;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.repository.LivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
