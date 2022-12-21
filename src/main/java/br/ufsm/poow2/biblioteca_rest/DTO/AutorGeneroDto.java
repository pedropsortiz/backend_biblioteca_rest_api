package br.ufsm.poow2.biblioteca_rest.DTO;

import br.ufsm.poow2.biblioteca_rest.model.Autor;
import br.ufsm.poow2.biblioteca_rest.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AutorGeneroDto {

    private Integer idAutorGenero;
    private Integer idAutor;
    private Integer idGenero;

}
