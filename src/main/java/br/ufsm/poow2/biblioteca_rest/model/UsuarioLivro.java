package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UsuarioLivro")
public class UsuarioLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuarioLivro;

    @Column(name = "dataEmprestimoUsuarioLivro")
    private @NotNull  @NotBlank Date dataEmprestimoUsuarioLivro;

    @Column(name = "dataDevolucaoUsuarioLivro")
    private @NotNull @NotBlank Date dataDevolucaoUsuarioLivro;

    @OneToOne
    private @NotNull @NotBlank Usuario usuario;

    @OneToOne
    private @NotNull @NotBlank Livro livro;

    @Column(name = "statusUsuarioLivro" )
    private @NotNull @NotBlank StatusUsuarioLivro statusUsuarioLivro;

    public enum StatusUsuarioLivro { A, B, L }

}
