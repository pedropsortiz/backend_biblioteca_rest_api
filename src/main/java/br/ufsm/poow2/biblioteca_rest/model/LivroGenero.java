package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LivroGenero {

    private int idLivro;
    private int idGenero;

}
