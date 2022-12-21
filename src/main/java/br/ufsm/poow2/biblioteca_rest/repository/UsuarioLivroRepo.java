package br.ufsm.poow2.biblioteca_rest.repository;

import br.ufsm.poow2.biblioteca_rest.model.UsuarioLivro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioLivroRepo extends JpaRepository<UsuarioLivro, Integer> {
}
