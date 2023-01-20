package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.common.ApiResponse;
import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class UserException {

    @Autowired
    UserRepository userRepository;
    private static final String NAME_REGEX = "^[A-Za-z\u00C0-\u017FÇç\s][A-Za-z\u00C0-\u017FÇç\s]([A-Za-z\u00C0-\u017FÇç\s])$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=])[A-Za-z0-9!@#$%^&*()_+-=]{8,}$";

        public Map<String, String> handleAddUserErrors(User user) {
            ApiResponse apiResponse = new ApiResponse(false, "Falha ao criar novo usuário.");

        if (!isEmailValid(user.getEmail())) {
            apiResponse.addError("email", "Email inválido. O email deve conter um nome de usuário seguido de um sinal '@' e um domínio, por exemplo, 'usuario@dominio.com'");        }

        if (doesUserExistByEmail(user.getEmail())) {
            apiResponse.addError("email", "Email já em uso");
        }

        if (isPermissionValid(user.getPermission())) {
            apiResponse.addError("permission",  "Papéis de usuário não reconhecidos.");
        }

        if (!isNameValid((user.getName()))) {
            apiResponse.addError("name", "Nome de usuário inválido. O nome de usuário deve ser maior que uma palavra e pode conter apenas letras acentos e o caractere 'ç'");
        }

        if (!isPasswordValid(user.getPassword())) {
            apiResponse.addError("password", "Senha inválida. A senha deve ter pelo menos 8 caracteres e deve conter pelo menos uma letra maiúscula, um número e um caractere especial");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleUpdateUserErrors(Integer id, User user) {
        ApiResponse apiResponse = new ApiResponse(false, "Falha ao criar novo usuário.");
        User userRepo = userRepository.findById(id).orElse(null);

        if (userRepo.equals(null)){
            apiResponse.addError("name", "Usuário não encontrado ou não existe!");
        }else{
            if (user.getPassword().equals(userRepo.getPassword())){
                apiResponse.setErrors(handleAddUserErrors(user));
            }else {
                apiResponse.addError("password", "Erro ao atualizar perfil. Senha incorreta");
            }
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleDeleteUserErrors(Integer id) {
        ApiResponse apiResponse = new ApiResponse();

        if (id == null){
            apiResponse.addError("id", "O ID do usuário não pode ser nulo.");
        }
        else if (!doesUserExistById(id)){
            apiResponse.addError("name", "O usuário especificado não foi encontrado ou não existe.");
        }

        return apiResponse.getErrors();
    }

    public Map<String, String> handleUpdateTokenErrors(String email, String jwtToken) {
        ApiResponse apiResponse = new ApiResponse();

        if (email == null || email.trim().isEmpty()) {
            apiResponse.addError("email", "O email não pode ser nulo ou vazio");
        }
        if (jwtToken == null || jwtToken.trim().isEmpty()) {
            apiResponse.addError("token", "O token JWT não pode ser nulo ou vazio");
        }
        if (!doesUserExistByEmail(email)) {
            apiResponse.addError("email","O usuário com o email especificado não foi encontrado");
        }

        return apiResponse.getErrors();
    }

    public boolean doesUserExistById(Integer id) {
        return userRepository.findById(id) != null;
    }

    public boolean doesUserExistByEmail(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    public boolean isEmailValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean isPermissionValid(String permission) {
        return Objects.equals(permission, "USR") || Objects.equals(permission, "ADM");
    }

    public boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

}
