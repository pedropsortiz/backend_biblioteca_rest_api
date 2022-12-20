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
public class LivroDto {

    private Integer idLivro;
    private @NotNull @NotBlank String nomeLivro;
    private String descricaoLivro;
    private String urlCapaLivro;
    private @NotNull @NotBlank Integer qntdTotalLivro;
    private @NotNull @NotBlank Integer qntdEmUsoLivro;
    private @NotNull @NotBlank Integer idAutor;

}
