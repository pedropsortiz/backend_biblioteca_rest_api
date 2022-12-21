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
@Table(name = "Emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprestimo;

    @Column(name = "dataEmprestimoUsuarioLivro")
    private @NotNull  @NotBlank Date dataEmprestimoUsuarioLivro;

    @Column(name = "dataDevolucaoUsuarioLivro")
    private @NotNull @NotBlank Date dataDevolucaoUsuarioLivro;

    @OneToOne
    private @NotNull @NotBlank Usuario usuario;

    @OneToOne
    private @NotNull @NotBlank Livro livro;

    @Column(name = "statusEmprestimo" )
    private @NotNull @NotBlank StatusEmprestimo statusEmprestimo;

    public enum StatusEmprestimo { A, B, L }

}
