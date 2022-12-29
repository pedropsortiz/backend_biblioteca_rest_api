package br.ufsm.poow2.biblioteca_rest.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse {

    private boolean success;
    private String message;
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
