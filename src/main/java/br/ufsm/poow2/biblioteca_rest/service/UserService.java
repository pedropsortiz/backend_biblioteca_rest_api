package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<ApiResponse> addUser(User user) {
        String password = user.getPassword();
        String email = user.getEmail();
        String permission = user.getPermission();

        // Validate email
        final String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(emailRegex)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Failed to create new user. Invalid email"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Check if email is already in use
        User existingEmail = userRepository.findUserByEmail(email);
        if (existingEmail != null) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Failed to create new user. Email already in use"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validate user role
        if (!permission.equals("usr") && !permission.equals("adm")) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Failed to create new user. Unrecognized user roles."),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validate user name
        final String nameRegex = "^[A-Za-z\\u00C0-\\u017F]+$";
        if (!user.getName().matches(nameRegex)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Failed to create new user. Invalid user name"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validate password
        final String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=])[A-Za-z0-9!@#$%^&*()_+-=]{8,}$";
        if (!password.matches(passwordRegex)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Failed to create new user. Invalid password"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Attempt to create a new user
        try {
            // Encode user password using BCrypt
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setToken(null);

            // Save user to repository
            userRepository.save(user);
            return new ResponseEntity<>(
                    new ApiResponse(true, "New user created successfully!"), HttpStatus.CREATED);
        } catch (Exception e) {
            HttpStatus status;
            String message;

            // Determine error type based on thrown exception
            if (e instanceof DataAccessException) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                message = "Failed ao criar usuário devido a um problema de acesso aos dados: " + e.getMessage();
            } else if (e instanceof ConstraintViolationException) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                message = "Falha ao criar usuário devido a um problema de inicialização: " + e.getMessage();
            } else {
                // Trata qualquer outro tipo de exceção de forma genérica
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                message = "Falha ao criar usuário devido a um erro desconhecido: " + e.getMessage();
            }
            // Retorna a resposta com os detalhes do erro
            return new ResponseEntity<>(new ApiResponse(false, message), status);
        }
    }

    public void updateJwtToken(String email, String jwtToken) {
        User user = userRepository.findUserByEmail(email);
        user.setToken(jwtToken);
        userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<ApiResponse> updateUser(Integer id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);

        String password = updatedUser.getPassword();
        String email = updatedUser.getEmail();
        String permission = updatedUser.getPermission();

        // Validate email
        final String emailRegex = "^[\\w-_\\.+]*[\\w-_\\.]@([\\w]+\\.)+[\\w]+[\\w]$";
        if (!email.matches(emailRegex)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Failed to update user. Invalid email"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Check if email is already in use
        User existingEmail = userRepository.findUserByEmail(email);
        if (existingEmail != null) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Failed to update user. Email already in use"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validate user role
        if (!permission.equals("usr") && !permission.equals("adm")) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Falha ao editar usuário. Permissões de usuário não reconhecidas."),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validação de nome de usuário
        final String nomeRegex = "^[A-Za-z\\u00C0-\\u017F]+$";
        if (!updatedUser.getName().matches(nomeRegex)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Falha ao editar usuário. Nome de usuário inválido"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Validação de senha
        final String senhaRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=])[A-Za-z0-9!@#$%^&*()_+-=]{8,}$";
        if (!password.matches(senhaRegex)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "Falha ao editar usuário. Senha inválida"),
                    HttpStatus.BAD_REQUEST
            );
        }

        // Tentativa para criar um novo usuário
        try {
            // Codifica a senha do usuário usando BCrypt
            user.get().update(updatedUser);
            userRepository.save(user.get());

            return new ResponseEntity<>(
                    new ApiResponse(true, "Usuário editado com sucesso!"), HttpStatus.CREATED);
        } catch (Exception e) {
            HttpStatus status;
            String mensagem;

            // Determina o tipo de erro de acordo com a exceção lançada
            if (e instanceof DataAccessException) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                mensagem = "Falha ao editar usuário devido a um problema de acesso aos dados: " + e.getMessage();
            } else if (e instanceof ConstraintViolationException) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                mensagem = "Falha ao editar usuário devido a um problema de inicialização: " + e.getMessage();
            } else {
                // Trata qualquer outro tipo de exceção de forma genérica
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                mensagem = "Falha ao editar usuário devido a um erro desconhecido: " + e.getMessage();
            }
            // Retorna a resposta com os detalhes do erro
            return new ResponseEntity<>(new ApiResponse(false, mensagem), status);
        }

    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean doesUserExists(Integer idUsuario) {
        return userRepository.existsById(idUsuario);
    }

    public ResponseEntity<ApiResponse> deleteUserById(Integer userId) {
        // Verifica se o ID do usuário é nulo
        if (userId == null) {
            return new ResponseEntity<>(new ApiResponse(false, "User ID cannot be null."), HttpStatus.OK);
        }

        // Verifica se o usuário com o ID especificado existe
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "User with specified ID does not exist."), HttpStatus.OK);
        }

        // Exclui o usuário
        userRepository.deleteById(userId);

        // Retorna uma resposta da API com o resultado da operação
        return new ResponseEntity<>(new ApiResponse(true, "User was successfully deleted!"), HttpStatus.OK);
    }

}
