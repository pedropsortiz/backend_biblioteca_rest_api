package br.ufsm.poow2.biblioteca_rest.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer id;
    private @NotNull @NotBlank String title;
    private String description;
    private String coverUrl;
    private @NotNull @NotBlank Integer totalQuantity;
    private @NotNull @NotBlank Integer inUseQuantity;
    private @NotNull @NotBlank Integer authorId;
    private @NotNull @NotBlank int edition;
    private @NotNull @NotBlank Date publicationDate;
}
