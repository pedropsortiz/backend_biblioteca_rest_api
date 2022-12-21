package br.ufsm.poow2.biblioteca_rest.DTO;

import br.ufsm.poow2.biblioteca_rest.model.Livro;
import br.ufsm.poow2.biblioteca_rest.model.Usuario;
import br.ufsm.poow2.biblioteca_rest.model.Emprestimo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmprestimoDto {

    private Integer idEmprestimo;
    private @NotNull @NotBlank Date dataEmprestimoUsuarioLivro;
    private @NotNull @NotBlank Date dataDevolucaoUsuarioLivro;
    private @NotNull @NotBlank Integer idUsuario;
    private @NotNull @NotBlank Integer idLivro;
    private @NotNull @NotBlank Emprestimo.StatusEmprestimo statusEmprestimo;

}
