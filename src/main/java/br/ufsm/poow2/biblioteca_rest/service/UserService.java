package br.ufsm.poow2.biblioteca_rest.service;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.exception.UserException;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.UserRepository;
import br.ufsm.poow2.biblioteca_rest.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserException userException;

    @Autowired
    public UserService(UserRepository userRepository, UserException userException) {
        this.userRepository = userRepository;
        this.userException = userException;
    }

    public ResponseEntity<ApiResponse> addUser(User user) {
        ResponseEntity<ApiResponse> response;
        List<String> handleErrors = userException.handleAddUserErrors(user);

        if (handleErrors.isEmpty()){
            try {
                user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                user.setToken(new JWTUtil().geraToken(user));
                userRepository.save(user);
                response = ResponseEntity.status(HttpStatus.CREATED).body(
                        new ApiResponse(true, "Novo usuário criado com sucesso!"));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao criar novo usuário. " + String.join(" ", handleErrors))
            );
        }
        return response;
    }

    public void updateJwtToken(String email, String jwtToken) {
        List<String> handleErrors = userException.handleUpdateTokenErrors(email, jwtToken);
        if (handleErrors.isEmpty()) {
            User user = userRepository.findUserByEmail(email);
            user.setToken(jwtToken);
            userRepository.save(user);
        }
        else{
            System.out.println(handleErrors);
        }
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<ApiResponse> updateUser(Integer id, User updatedUser) {
        Optional<User> user = userRepository.findById(id);
        ResponseEntity<ApiResponse> response;
        List<String> handleErrors = userException.handleUpdateUserErrors(id, updatedUser);

        if (handleErrors.isEmpty()){
            try {
                user.get().update(updatedUser);
                userRepository.save(user.get());
                response = ResponseEntity.status(HttpStatus.CREATED).body(
                        new ApiResponse(true, "Usuário editado com sucesso!"));

            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao atualizar usuário. " + String.join(" ", handleErrors))
            );
        }
        return response;

    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public ResponseEntity<ApiResponse> deleteUserById(Integer userId) {
        ResponseEntity<ApiResponse> response;
        List<String> handleErrors = userException.handleDeleteUserErrors(userId);

        if (handleErrors.isEmpty()){
            try {
                userRepository.deleteById(userId);
                response = ResponseEntity.status(HttpStatus.CREATED).body(
                        new ApiResponse(true, "Usuário deletado com sucesso!"));
            } catch (DataAccessException e) {
                response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ApiResponse(false, "Ocorreu um erro ao acessar o banco de dados. Por favor, tente novamente mais tarde."));
            }
        }
        else
        {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse(false, "Falha ao deletar usuário. " + String.join(" ", handleErrors))
            );
        }
        return response;

    }

}
