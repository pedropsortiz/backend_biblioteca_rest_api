package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    private @NotBlank Date dataEmprestimoUsuarioLivro;

    @Column(name = "dataDevolucaoUsuarioLivro")
    private @NotBlank Date dataDevolucaoUsuarioLivro;

    @OneToOne
    private @NotBlank Usuario idUsuario;

    @OneToOne
    private @NotBlank Livro idLivro;

    @Column(name = "statusUsuarioLivro" )
    private @NotBlank StatusUsuarioLivro statusUsuarioLivro;

    public enum StatusUsuarioLivro { A, B, L }

}
