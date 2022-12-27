package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepo usuarioRepo;

    public ResponseEntity<ApiResponse> criarUsuario(Usuario usuario){
        String senha = usuario.getSenhaUsuario();
        usuario.setSenhaUsuario(new BCryptPasswordEncoder().encode(senha));
        usuario.setTokenUsuario(null);
        Usuario emailExiste = usuarioRepo.findUsuarioByEmailUsuario(usuario.getEmailUsuario());
        if (emailExiste == null){
            if (usuario.getPermissaoUsuario().equals("usr") || usuario.getPermissaoUsuario().equals("adm")){
                usuarioRepo.save(usuario);
                return new ResponseEntity<>(new ApiResponse(true, "Novo usuario criado com sucesso!"), HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo usuário.Permissões de usuário não recohecidas."), HttpStatus.BAD_REQUEST);
            }
        } else{
            return new ResponseEntity<>(new ApiResponse(false, "Falha ao criar novo usuário. Email já utilizado"), HttpStatus.BAD_REQUEST);
        }
    }

    public void atualizarTokenJWT(String emailUsuario, String tokenJWT){
        Usuario usuario = usuarioRepo.findUsuarioByEmailUsuario(emailUsuario);
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
        return usuarioRepo.findUsuarioByEmailUsuario(emailUsuario);
    }

    public boolean findById(Integer idUsuario) {
        return usuarioRepo.findById(idUsuario).isPresent();
    }

    public void deletarUsuario(Integer idUsuario) {
        usuarioRepo.deleteById(idUsuario);
    }
}
