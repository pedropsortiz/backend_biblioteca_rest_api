package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.model.Book;
import br.ufsm.poow2.biblioteca_rest.model.Loan;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.sql.Date;
import java.util.Map;
import java.util.Optional;

@ControllerAdvice
public class AuthorException {

    @Autowired
    AuthorRepository authorRepository;

    private static final String AUTHOR_REGEX = "^[A-Za-zÀ-ÿ][A-Za-zÀ-ÿ\\s]*$";

    public Map<String, String> handleAddAuthorErrors(Author author) {
        ApiResponse apiResponse = new ApiResponse();

        if (!doesAuthorExists(author.getName()))
        {
            apiResponse.addError("name", "Falha ao criar o autor. O autor já está registrado no banco de dados.");
        }
        if (!isNameValid(author.getName()))
        {
            apiResponse.addError("name", "Falha ao criar o autor. O nome do autor deve conter apenas letras, acentos ou o caractere especial ç.");
        }
        if (!isDeathDateValid(author.getDeathDate(), author.getBirthDate()))
        {
            apiResponse.addError("death_date", "Falha ao criar o autor. A data de morte ocorre antes da data de nascimento.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleUpdateAuthorErrors(Integer id, Author updatedAuthor) {
        ApiResponse apiResponse = new ApiResponse();
        Author author = authorRepository.findById(id).orElse(null);

        if (!doesAuthorExists(author.getName()))
        {
            apiResponse.addError("author", "Falha ao criar o autor. O autor já está registrado no banco de dados.");
        }
        if (!doesAuthorExists(updatedAuthor.getName()))
        {
            // Retorna mensagem de erro caso o autor não exista
            apiResponse.addError("name", "O autor não existe ou não foi encontrado!");
        }
        if (!isNameValid(author.getName()))
        {
            apiResponse.addError("name", "Falha ao criar o autor. O nome do autor deve conter apenas letras, acentos ou o caractere especial ç.");
        }
        if (!isDeathDateValid(author.getDeathDate(), author.getBirthDate()))
        {
            apiResponse.addError("birth_date", "Falha ao editar o autor. A data de morte ocorre depois da data de nascimento.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleDeleteAuthorByIdErrors(Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        Optional<Author> author = authorRepository.findById(id);

        // Verifica se o autor foi encontrado
        if (!doesAuthorExists(author.get().getName()))
        {
            apiResponse.addError("name", "O autor não existe ou não foi encontrado!");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleGetAuthorByIdErrors(Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        Author author = authorRepository.findById(id).orElse(null);

        if (!doesAuthorExists(author.getName()))
        {
            apiResponse.addError("idAuthor", "Autor não encontrado");
        }

        return apiResponse.getErrors();
    }

        /*
    Testes
     */

    private boolean doesAuthorExists(String name){
        return authorRepository.findAuthorByName(name) != null;
    }

    private boolean isNameValid(String name){
        return name.matches(AUTHOR_REGEX);
    }

    private boolean isDeathDateValid(Date dateOfDeath, Date dateOfBirth) {
        // Verifica se a data de morte é nula ou se a data de morte ocorre depois da data de nascimento
        return dateOfDeath == null || dateOfDeath.after(dateOfBirth);
    }

}
