package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public ResponseEntity<ApiResponse> addAuthor(Author author) {
        ResponseEntity<ApiResponse> response;

        String regexName = "[a-zA-ZÇç\\u00C0-\\u017F\\s]+";

        boolean isNameValid = author.getName().matches(regexName);
        boolean isDeathDateValid = isValidDeathDate(author.getDeathDate(), author.getBirthDate());

        if (!isNameValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao criar o autor. O nome do autor deve conter apenas letras, acentos ou o caractere especial ç."));
        }
        else if (!isDeathDateValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao criar o autor. A data de morte ocorre antes da data de nascimento."));
        }
        else
        {
            try {
                authorRepository.save(author);
                response = ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Novo autor criado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }


    public List<Author> listAllAuthors() {
        // Retorna todos os autores do repositório
        return authorRepository.findAll();
    }

    public ResponseEntity<ApiResponse> updateAuthor(Integer id, Author updatedAuthor) {
        Author author = authorRepository.findById(id).orElse(null);
        ResponseEntity<ApiResponse> response;

        String regexName = "[a-zA-ZÇç\\u00C0-\\u017F\\s]+";

        boolean isNameValid = updatedAuthor.getName().matches(regexName);
        boolean isDeathDateValid = isValidDeathDate(updatedAuthor.getDeathDate(), updatedAuthor.getBirthDate());
        boolean doesAuthorExists = (author == null) ? false : true;

        // Verifica se o autor foi encontrado
        if (!doesAuthorExists)
        {
            // Retorna mensagem de erro caso o autor não exista
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "O autor não existe ou não foi encontrado!"));
        }
        else if (!isNameValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao criar o autor. O nome do autor deve conter apenas letras, acentos ou o caractere especial ç."));
        }
        else if (!isDeathDateValid)
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao editar o autor. A data de morte ocorre depois da data de nascimento."));
        }
        else
        {
            try {
                author.update(updatedAuthor);
                authorRepository.save(author);
                response = ResponseEntity.ok(new ApiResponse(true, "O autor foi editado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }

    public ResponseEntity<ApiResponse> deleteAuthorById(Integer id) {
        Optional<Author> author = authorRepository.findById(id);
        ResponseEntity<ApiResponse> response;

        boolean doesAuthorExists = author.isPresent();

        // Verifica se o autor foi encontrado
        if (!doesAuthorExists)
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "O autor não existe ou não foi encontrado!"));
        }
        else
        {
            // Exclui o autor do repositório
            try {
                authorRepository.deleteById(id);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O autor foi excluido com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        return response;
    }

    private boolean isValidDeathDate(Date dateOfDeath, Date dateOfBirth) {
        // Verifica se a data de morte é nula ou se a data de morte ocorre depois da data de nascimento
        return dateOfDeath == null || dateOfDeath.after(dateOfBirth);
    }

    public ResponseEntity<ApiResponse> getAuthorById(Integer id) {
        Author author = authorRepository.findById(id).orElse(null);
        ResponseEntity<ApiResponse> response;

        boolean doesAuthorExists = (author == null) ? false : true;

        if (!doesAuthorExists)
        {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Autor não encontrado"));
        }
        else
        {
            try {
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, author.toString()));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Erro de acesso a data"));
            }
        }
        return response;
    }

    public Optional<Author> getOneAuthor(Integer id){
        return authorRepository.findById(id);
    }

}
