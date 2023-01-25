package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.exception.AuthorException;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorException authorException;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorException authorException) {
        this.authorRepository = authorRepository;
        this.authorException = authorException;
    }

    public ResponseEntity<ApiResponse> addAuthor(Author author) {
        ResponseEntity<ApiResponse> response;
        Map<String, String> handleErrors = authorException.handleAddAuthorErrors(author);

        if (handleErrors.isEmpty()) {
            try {
                authorRepository.save(author);
                response = ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Novo autor criado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao adicionar autor.", handleErrors)
            );
        }

        return response;
    }


    public List<Author> listAllAuthors() {
        // Retorna todos os autores do repositório
        return authorRepository.findAll();
    }

    public ResponseEntity<ApiResponse> updateAuthor(Integer id, Author updatedAuthor) {
        ResponseEntity<ApiResponse> response;
        Author author = authorRepository.findById(id).orElse(null);

        Map<String, String> handleErrors = authorException.handleUpdateAuthorErrors(id, updatedAuthor);

        if (handleErrors.isEmpty()) {
            try {
                author.update(updatedAuthor);
                authorRepository.save(author);
                response = ResponseEntity.ok(new ApiResponse(true, "O autor foi editado com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao atualizar autor.", handleErrors)
            );
        }

        return response;
    }

    public ResponseEntity<ApiResponse> deleteAuthorById(Integer id) {
        ResponseEntity<ApiResponse> response;
        Map<String, String> handleErrors = authorException.handleDeleteAuthorByIdErrors(id);

        if (handleErrors.isEmpty())
        {
            // Exclui o autor do repositório
            try {
                authorRepository.deleteById(id);
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O autor foi excluido com sucesso!"));
            } catch (DataAccessException e){
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao deletar autor.", handleErrors)
            );
        }
        return response;
    }

    public ResponseEntity<ApiResponse> getAuthorById(Integer id) {
        ResponseEntity<ApiResponse> response;
        Map<String, String> handleErrors = authorException.handleGetAuthorByIdErrors(id);
        Author author = authorRepository.findById(id).orElse(null);


        if (handleErrors.isEmpty())
        {
            try {
                response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, author.toString()));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, "Erro de acesso a data"));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao encontrar autor.", handleErrors)
            );
        }

        return response;
    }

    public Optional<Author> getOneAuthor(Integer id){
        return authorRepository.findById(id);
    }

    public Optional<Author> findById(Integer id) {
        return authorRepository.findById(id);
    }
}
