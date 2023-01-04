package br.ufsm.poow2.biblioteca_rest.exception;

import br.ufsm.poow2.biblioteca_rest.model.User;
import br.ufsm.poow2.biblioteca_rest.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UserException {

    @Autowired
    UserRepository userRepository;
    private static final String NAME_REGEX = "^[A-Za-z\\u00C0-\\u017FÇç]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+-=])[A-Za-z0-9!@#$%^&*()_+-=]{8,}$";

    @ExceptionHandler(Exception.class)
    public List<String> handleAddUserErrors(User user) {
        List<String> errors = new ArrayList<>();

        if (!isEmailValid(user.getEmail())) {
            errors.add("Email inválido. O email deve conter um nome de usuário seguido de um sinal '@' e um domínio, por exemplo, 'usuario@dominio.com'");
        }

        if (doesUserExistByEmail(user.getEmail())) {
            errors.add("Email já em uso");
        }

        if (!isPermissionValid(user.getPermission())) {
            errors.add("Papéis de usuário não reconhecidos.");
        }

        if (!isNameValid((user.getName()))) {
            errors.add("Nome de usuário inválido. O nome de usuário pode conter apenas letras acentos e o caractere 'ç'");
        }

        if (!isPasswordValid(user.getPassword())) {
            errors.add("Senha inválida. A senha deve ter pelo menos 8 caracteres e deve conter pelo menos uma letra maiúscula, um número e um caractere especial");
        }

        return errors;
    }

    @ExceptionHandler(Exception.class)
    public List<String> handleUpdateUserErrors(Integer id, User user) {
        List<String> errors = new ArrayList<>();
        User userRepo = userRepository.findById(id).orElse(null);

        if (userRepo.equals(null)){
            errors.add("Usuário não encontrado ou não existe!");
        }else{
            if (user.getPassword().equals(userRepo.getPassword())){
                errors = handleAddUserErrors(user);
            }else {
                errors.add("Erro ao atualizar perfil. Senha incorreta");
            }
        }

        return errors;
    }

    @ExceptionHandler(Exception.class)
    public List<String> handleDeleteUserErrors(Integer id) {
        List<String> errors = new ArrayList<>();

        if (id == null){
            errors.add("O ID do usuário não pode ser nulo.");
        }
        else if (!doesUserExistById(id)){
            errors.add("O usuário especificado não foi encontrado ou não existe.");
        }

        return errors;
    }

    @ExceptionHandler(Exception.class)
    public List<String> handleUpdateTokenErrors(String email, String jwtToken) {
        List<String> errors = new ArrayList<>();

        if (email == null || email.trim().isEmpty()) {
            errors.add("O email não pode ser nulo ou vazio");
        }
        if (jwtToken == null || jwtToken.trim().isEmpty()) {
            errors.add("O token JWT não pode ser nulo ou vazio");
        }
        if (!doesUserExistByEmail(email)) {
            errors.add("O usuário com o email especificado não foi encontrado");
        }

        return errors;
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
        return permission == "USR"|| permission == "ADM";
    }

    public boolean isNameValid(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    public boolean isPasswordValid(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

}
