package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Genero")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGenero;

    @Column(name = "nomeGenero")
    private @NotNull  @NotBlank String nomeGenero;

    public void update(Genero novosValores) {
        this.nomeGenero = novosValores.nomeGenero;
    }
}
