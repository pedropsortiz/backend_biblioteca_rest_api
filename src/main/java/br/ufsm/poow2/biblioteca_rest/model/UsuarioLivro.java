package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UsuarioLivro")
public class UsuarioLivro {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuarioLivro;

    @Column(name = "dataEmprestimoUsuarioLivro")
    private @NotBlank Date dataEmprestimoUsuarioLivro;

    @Column(name = "dataDevolucaoUsuarioLivro")
    private @NotBlank  Date dataDevolucaoUsuarioLivro;

    private int idUsuario;

    private int idLivro;

    @Column(name = "statusUsuarioLivro")
    private @NotBlank  StatusUsuarioLivro statusUsuarioLivro;

    public enum StatusUsuarioLivro { aprovacao, emprestado, atrasado }

}
