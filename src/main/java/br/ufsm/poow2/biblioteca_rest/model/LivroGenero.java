package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "LivroGenero")
public class LivroGenero {

    private int idLivro;
    private int idGenero;

}
