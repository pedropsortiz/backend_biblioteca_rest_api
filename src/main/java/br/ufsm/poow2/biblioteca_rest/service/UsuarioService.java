package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepo usuarioRepo;

    public void criarUsuario(Usuario usuario){
        usuarioRepo.save(usuario);
    }

    public List<Usuario> listarUsuario(){
        return usuarioRepo.findAll();
    }

    public void editarUsuario(Integer idUsuario, Usuario editarUsuario){
        Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);
        usuario.get().setNomeUsuario(editarUsuario.getNomeUsuario());
        usuario.get().setSenhaUsuario(editarUsuario.getSenhaUsuario());
        usuario.get().setEmailUsuario(editarUsuario.getEmailUsuario());
        usuarioRepo.save(usuario.get());
    }

    public boolean findById(Integer idUsuario) {
        return usuarioRepo.findById(idUsuario).isPresent();
    }

}