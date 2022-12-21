package br.ufsm.poow2.biblioteca_rest.DTO;

import br.ufsm.poow2.biblioteca_rest.model.Genero;
import br.ufsm.poow2.biblioteca_rest.model.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroGeneroDto {

    private Integer idLivroGenero;
    private Integer idLivro;
    private Integer idGenero;

}
