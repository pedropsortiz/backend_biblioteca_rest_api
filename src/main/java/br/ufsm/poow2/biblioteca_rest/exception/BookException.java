package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BookException {

    @Autowired
    BookRepository bookRepository;

}
