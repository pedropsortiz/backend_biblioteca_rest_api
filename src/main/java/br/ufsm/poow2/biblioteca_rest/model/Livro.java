package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Livro")
public class Livro {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLivro;

    @Column(name = "nomeLivro")
    private @NotBlank String nomeLivro;

    @Column(name = "descricaoLivro")
    private String descricaoLivro;

    private int idAutor;

    @Column(name = "urlCapaLivro")
    private String urlCapaLivro;

    @Column(name = "qntdTotalLivro")
    private @NotBlank Integer qntdTotalLivro;

    @Column(name = "qntdEmUsoLivro")
    private @NotBlank Integer qntdEmUsoLivro;

}
