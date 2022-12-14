package br.ufsm.poow2.biblioteca_rest.repository;

import br.ufsm.poow2.biblioteca_rest.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    @Transactional(readOnly = true)
    Genre findGenreByName(String nameGenre);

}
