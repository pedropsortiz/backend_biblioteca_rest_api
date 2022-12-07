package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "UsuarioLivro")
public class UsuarioLivro {

    @Id
    @SequenceGenerator(
            name = "usuariolivro_sequence",
            sequenceName = "usuariolivro_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuariolivro_sequence"
    )
    @Column(
            name = "idUsuarioLivro",
            updatable = false
    )
    private Long idUsuarioLivro;

    @Column(
            name = "dataEmprestimoUsuarioLivro",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date dataEmprestimoUsuarioLivro;

    @Column(
            name = "dataDevolucaoUsuarioLivro",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date dataDevolucaoUsuarioLivro;

    private int idUsuario;

    private int idLivro;

    @Column(
            name = "statusUsuarioLivro",
            nullable = false,
            columnDefinition = "CHAR (1)"
    )
    private StatusUsuarioLivro statusUsuarioLivro;

    public enum StatusUsuarioLivro { A, B, L }

}
