package br.ufsm.poow2.biblioteca_rest.repository;

import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Transactional(readOnly = true)
    Book findBookByTitleAndAuthorAndEditionAndPublicationDate(String title,
                                                              Author author,
                                                              int edition,
                                                              Date publicationDate);
}
