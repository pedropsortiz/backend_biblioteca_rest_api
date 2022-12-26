package br.ufsm.poow2.biblioteca_rest.repository;

import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Transactional(readOnly = true)
    Usuario findUsuarioByEmailUsuario(String email);
}
