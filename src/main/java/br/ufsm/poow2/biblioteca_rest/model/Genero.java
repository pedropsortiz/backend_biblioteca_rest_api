package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "Genero")
public class Genero {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "idGenero",
            updatable = false
    )
    private Integer idGenero;

    @Column(
            name = "nomeGenero",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String nomeGenero;

}
