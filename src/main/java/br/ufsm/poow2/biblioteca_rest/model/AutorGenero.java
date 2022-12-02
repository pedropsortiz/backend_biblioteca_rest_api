package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AutorGenero")
public class AutorGenero {

    private Integer idAutor;

    private Integer idGenero;

}
