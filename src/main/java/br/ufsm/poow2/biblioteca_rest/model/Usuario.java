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

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    @Column(name = "nomeUsuario")
    private @NotBlank String nomeUsuario;

    @Column(name = "nicknameUsuario")
    private @NotBlank String nicknameUsuario;

    @Column(name = "emailUsuario")
    private @NotBlank String emailUsuario;

    @Column(name = "senhaUsuario")
    private @NotBlank String senhaUsuario;

    @Column(name = "permissaoUsuario")
    private @NotBlank String permissaoUsuario;

    @Column(name = "tokenUsuario")
    private String tokenUsuario;

}