package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioLivro {

    private int idUsuarioLivro;
    private Date dataEmprestimoUsuarioLivro;
    private Date dataDevolucaoUsuarioLivro;
    private int idUsuario;
    private int idLivro;
    private StatusUsuarioLivro statusUsuarioLivro;

    public enum StatusUsuarioLivro { aprovacao, emprestado, atrasado }

}
