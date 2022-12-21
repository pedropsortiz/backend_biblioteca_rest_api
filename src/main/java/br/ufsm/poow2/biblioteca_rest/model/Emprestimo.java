package br.ufsm.poow2.biblioteca_rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
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

    @Column(name = "dataEmprestimo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataEmprestimo;

    @Column(name = "dataDevolucao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataDevolucao;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;

    @Column(name = "statusEmprestimo", nullable = false)
    private StatusEmprestimo statusEmprestimo;

    public enum StatusEmprestimo {
        ATRASADO, APROVADO, ESPERA
    }

}
