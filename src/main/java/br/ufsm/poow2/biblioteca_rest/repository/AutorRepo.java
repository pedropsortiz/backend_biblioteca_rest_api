package br.ufsm.poow2.biblioteca_rest.repository;

import br.ufsm.poow2.biblioteca_rest.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AutorRepo extends JpaRepository<Autor, Integer> {
    @Transactional(readOnly = true)
    Autor findAutorByNomeAutor(String nomeAutor);
    
}
