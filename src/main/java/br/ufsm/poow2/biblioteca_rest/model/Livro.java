package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;

    @Column(name = "nomeLivro")
    private @NotNull  @NotBlank String nomeLivro;

    @Column(name = "descricaoLivro")
    private String descricaoLivro;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private @NotNull @NotBlank Autor autor;

    @Column(name = "urlCapaLivro")
    private String urlCapaLivro;

    @Column(name = "qntdTotalLivro")
    private @NotNull @NotBlank Integer qntdTotalLivro;

    @Column(name = "qntdEmUsoLivro")
    private @NotNull @NotBlank Integer qntdEmUsoLivro;

}
