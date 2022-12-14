package br.ufsm.poow2.biblioteca_rest.repository;

import br.ufsm.poow2.biblioteca_rest.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, Integer> { }
