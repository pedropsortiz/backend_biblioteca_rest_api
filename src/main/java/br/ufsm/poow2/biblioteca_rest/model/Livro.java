package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLivro;

    @Column(name = "nomeLivro")
    private @NotBlank String nomeLivro;

    @Column(name = "descricaoLivro")
    private String descricaoLivro;

    @ManyToOne
    private @NotBlank Autor autor;

    @Column(name = "urlCapaLivro")
    private String urlCapaLivro;

    @Column(name = "qntdTotalLivro")
    private @NotBlank Integer qntdTotalLivro;

    @Column(name = "qntdEmUsoLivro")
    private @NotBlank Integer qntdEmUsoLivro;

}
