package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    final
    UsuarioRepo usuarioRepo;

    public UsuarioService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public boolean criarUsuario(Usuario usuario){
        String senha = usuario.getSenhaUsuario();
        usuario.setSenhaUsuario(new BCryptPasswordEncoder().encode(senha));

        if (usuario.getPermissaoUsuario().equals("usr") || usuario.getPermissaoUsuario().equals("adm")){
            usuarioRepo.save(usuario);
            return true;
        }
        return false;
    }

    public void atualizarTokenJWT(Usuario usuario, String tokenJWT){
        usuario.setTokenUsuario(tokenJWT);
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

    public Usuario getUsuarioByEmail(String emailUsuario){
        Usuario usuario = usuarioRepo.findByEmailUsuario(emailUsuario);
        return usuario;
    }

    public boolean findById(Integer idUsuario) {
        return usuarioRepo.findById(idUsuario).isPresent();
    }

}
