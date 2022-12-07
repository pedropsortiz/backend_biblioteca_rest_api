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

    @Id
    @SequenceGenerator(
            name = "genero_sequence",
            sequenceName = "genero_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "genero_sequence"
    )
    @Column(
            name = "idGenero",
            updatable = false
    )
    private Long idGenero;

    @Column(
            name = "nomeGenero",
            nullable = false,
            columnDefinition = "VARCHAR (255)"
    )
    private String nomeGenero;


}
