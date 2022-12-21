package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LivroGeneroRepo")
public class LivroGenero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivroGenero;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Genero genero;

}
