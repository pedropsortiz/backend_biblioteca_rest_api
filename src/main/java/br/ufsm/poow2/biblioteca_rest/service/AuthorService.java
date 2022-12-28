package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.Author;
import br.ufsm.poow2.biblioteca_rest.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public ResponseEntity<ApiResponse> createAuthor(Author author) {
        ResponseEntity<ApiResponse> response;

        // Verifica se a data de morte foi informada
        if (author.getDeathDate() != null) {
            // Verifica se a data de morte ocorre depois da data de nascimento
            if (!isValidDeathDate(author.getDeathDate(), author.getBirthDate())) {
                // Se a data de morte for anterior à data de nascimento, retorna uma resposta de erro
                response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao criar o autor. A data de morte ocorre antes da data de nascimento."));
                return response;
            }
        }
        // Salva o autor no banco de dados
        authorRepository.save(author);
        // Retorna uma resposta de sucesso
        response = ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Novo autor criado com sucesso!"));
        return response;
    }

    public List<Author> listAuthors() {
        // Retorna todos os autores do repositório
        return authorRepository.findAll();
    }

    public ResponseEntity<ApiResponse> updateAuthor(Integer id, Author updatedAuthor) {
        // Busca o autor no repositório pelo ID informado
        Author author = authorRepository.findById(id).orElse(null);
        ResponseEntity<ApiResponse> response;

        // Verifica se o autor foi encontrado
        if (author == null) {
            // Retorna mensagem de erro caso o autor não exista
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "O autor não existe ou não foi encontrado!"));
        }

        // Verifica se a data de morte é válida
        boolean isValidDeathDate = isValidDeathDate(updatedAuthor.getDeathDate(), updatedAuthor.getBirthDate());
        if (!isValidDeathDate) {
            // Retorna mensagem de erro caso a data de morte seja inválida
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Falha ao editar o autor. A data de morte ocorre depois da data de nascimento."));
        } else {
            // Atualiza os dados do autor
            author.update(updatedAuthor);
            // Salva as alterações no repositório
            authorRepository.save(author);
            // Retorna mensagem de sucesso
            response = ResponseEntity.ok(new ApiResponse(true, "O autor foi editado com sucesso!"));
        }

        return response;
    }

    public ResponseEntity<ApiResponse> deleteAuthor(Integer id) {
        // Busca o autor no repositório pelo ID informado
        Optional<Author> author = authorRepository.findById(id);
        ResponseEntity<ApiResponse> response;

        // Verifica se o autor foi encontrado
        if (!author.isPresent()) {
            // Retorna mensagem de erro caso o autor não exista
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "O autor não existe ou não foi encontrado!"));
            return response;
        }

        // Exclui o autor do repositório
        authorRepository.deleteById(id);
        // Retorna mensagem de sucesso
        response = ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "O autor foi excluido com sucesso!"));
        return response;
    }

    public boolean isAuthorIdValid(Integer id) {
        // Verifica se o ID informado corresponde a um autor existente no repositório
        return authorRepository.existsById(id);
    }

    private boolean isValidDeathDate(Date dateOfDeath, Date dateOfBirth) {
        // Verifica se a data de morte é posterior à data de nascimento
        return dateOfDeath.after(dateOfBirth);
    }

}
