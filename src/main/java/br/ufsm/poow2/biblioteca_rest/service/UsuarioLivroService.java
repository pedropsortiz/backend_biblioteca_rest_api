package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.DTO.LivroDto;
import br.ufsm.poow2.biblioteca_rest.DTO.UsuarioLivroDto;
import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.model.UsuarioLivro;
import br.ufsm.poow2.biblioteca_rest.repository.LivroRepo;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioLivroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioLivroService {

    @Autowired
    UsuarioLivroRepo usuarioLivroRepo;

    public void criarUsuarioLivro(UsuarioLivroDto usuarioLivroDto, Livro livro, Usuario usuario) {
        UsuarioLivro usuarioLivro = new UsuarioLivro();
        usuarioLivro.setUsuario(usuario);
        usuarioLivro.setLivro(livro);
        usuarioLivro.setStatusUsuarioLivro(usuarioLivroDto.getStatusUsuarioLivro());
        usuarioLivro.setDataEmprestimoUsuarioLivro(usuarioLivroDto.getDataEmprestimoUsuarioLivro());
        usuarioLivro.setDataDevolucaoUsuarioLivro(usuarioLivroDto.getDataDevolucaoUsuarioLivro());
        usuarioLivroRepo.save(usuarioLivro);
    }

    public UsuarioLivroDto getUsuarioLivroDto(UsuarioLivro usuarioLivro){
        UsuarioLivroDto usuarioLivroDto = new UsuarioLivroDto();
        usuarioLivro.setUsuario(usuarioLivro.getUsuario());
        usuarioLivro.setLivro(usuarioLivro.getLivro());
        usuarioLivro.setStatusUsuarioLivro(usuarioLivroDto.getStatusUsuarioLivro());
        usuarioLivro.setDataEmprestimoUsuarioLivro(usuarioLivroDto.getDataEmprestimoUsuarioLivro());
        usuarioLivro.setDataDevolucaoUsuarioLivro(usuarioLivroDto.getDataDevolucaoUsuarioLivro());
        return usuarioLivroDto;
    }

    public List<UsuarioLivroDto> getUsuarioLivros() {
        List<UsuarioLivro> usuarioLivroList = usuarioLivroRepo.findAll();

        List<UsuarioLivroDto> usuarioLivroDtos = new ArrayList<>();
        for (UsuarioLivro usuarioLivro: usuarioLivroList
        ) {
            usuarioLivroDtos.add(getUsuarioLivroDto(usuarioLivro));
        }
        return usuarioLivroDtos;
    }

    public void editarUsuarioLivro(UsuarioLivroDto usuarioLivroDto, Integer idUsuario) throws Exception {
        Optional<UsuarioLivro> usuarioLivroOptional = usuarioLivroRepo.findById(idUsuario);
        if (!usuarioLivroOptional.isPresent()){
            throw new Exception("O empréstimo não existe!");
        }
        UsuarioLivro usuarioLivro = usuarioLivroOptional.get();

        usuarioLivro.setUsuario(usuarioLivro.getUsuario());
        usuarioLivro.setLivro(usuarioLivro.getLivro());
        usuarioLivro.setStatusUsuarioLivro(usuarioLivroDto.getStatusUsuarioLivro());
        usuarioLivro.setDataEmprestimoUsuarioLivro(usuarioLivroDto.getDataEmprestimoUsuarioLivro());
        usuarioLivro.setDataDevolucaoUsuarioLivro(usuarioLivroDto.getDataDevolucaoUsuarioLivro());
        usuarioLivroRepo.save(usuarioLivro);
    }
}
