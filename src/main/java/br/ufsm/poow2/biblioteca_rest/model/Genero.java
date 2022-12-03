package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Genero")
@Table
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
