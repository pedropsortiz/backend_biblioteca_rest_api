package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "Livro")
public class Livro {

    @Id
    @SequenceGenerator(
            name = "livro_sequence",
            sequenceName = "livro_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "livro_sequence"
    )
    @Column(
            name = "idLivro",
            updatable = false
    )
    private Long idLivro;

    @Column(
            name = "nomeLivro",
            nullable = false,
            columnDefinition = "VARCHAR (255)"
    )
    private String nomeLivro;

    @Column(
            name = "descricaoLivro",
            nullable = false,
            columnDefinition = "VARCHAR (255)"
    )
    private String descricaoLivro;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @Column(
            name = "urlCapaLivro",
            columnDefinition = "VARCHAR (255)"
    )
    private String urlCapaLivro;

    @Column(
            name = "qntdTotalLivro",
            nullable = false,
            columnDefinition = "INT"
    )
    private Integer qntdTotalLivro;

    @Column(
            name = "qntdEmUsoLivro",
            nullable = false,
            columnDefinition = "INT"
    )
    private Integer qntdEmUsoLivro;

}
