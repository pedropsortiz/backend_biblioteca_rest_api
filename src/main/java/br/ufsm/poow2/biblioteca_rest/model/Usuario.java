package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "Usuario")
public class Usuario {

    @Id
    @SequenceGenerator(
            name = "usuario_sequence",
            sequenceName = "usuario_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuario_sequence"
    )
    @Column(
            name = "idUsuario",
            updatable = false
    )
    private Long idUsuario;

    @Column(
            name = "nomeUsuario",
            nullable = false,
            columnDefinition = "VARCHAR (255)"
    )
    private String nomeUsuario;

    @Column(
            name = "emailUsuario",
            nullable = false,
            unique = true,
            columnDefinition = "VARCHAR (255)"
    )
    private String emailUsuario;

    @Column(
            name = "senhaUsuario",
            nullable = false,
            columnDefinition = "VARCHAR (255)"
    )
    private @NotBlank String senhaUsuario;

    @Column(
            name = "permissaoUsuario",
            nullable = false,
            columnDefinition = "CHAR (3)"
    )
    private String permissaoUsuario;

    @Column(
            name = "tokenUsuario",
            columnDefinition = "VARCHAR (500)"
    )
    private String tokenUsuario;

}