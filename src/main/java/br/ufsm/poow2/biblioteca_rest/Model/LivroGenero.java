package br.ufsm.poow2.biblioteca_rest.Model;

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