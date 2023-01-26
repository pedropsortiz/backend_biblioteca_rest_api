package br.ufsm.poow2.biblioteca_rest.common;

import br.ufsm.poow2.biblioteca_rest.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    private boolean success;
    private String message;
    private Map<String, String> errors = new HashMap<>();

    private User user;
    private String token;

    public ApiResponse(boolean success, String message, User user, String token) {
        this.success = success;
        this.message = message;
        this.user = user;
        this.token = token;
    }

    public ApiResponse(boolean success, String message, Map<String, String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors;
    }

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public void addError(String field, String message) {
        this.errors.put(field, message);
    }

}
