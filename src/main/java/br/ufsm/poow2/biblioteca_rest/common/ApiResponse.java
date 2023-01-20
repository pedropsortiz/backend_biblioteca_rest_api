package br.ufsm.poow2.biblioteca_rest.common;

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

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(boolean success, String message, Map<String, String> handleErrors) {
        this.success = success;
        this.message = message;
        this.errors = handleErrors;
    }

    public void addError(String field, String message) {
        this.errors.put(field, message);
    }

}
