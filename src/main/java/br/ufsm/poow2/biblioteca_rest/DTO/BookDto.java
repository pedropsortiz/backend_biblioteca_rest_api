package br.ufsm.poow2.biblioteca_rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer id;
    private @NotNull @NotBlank String name;
    private String description;
    private String coverUrl;
    private @NotNull @NotBlank Integer totalQuantity;
    private @NotNull @NotBlank Integer inUseQuantity;
    private @NotNull @NotBlank Integer authorId;
}
