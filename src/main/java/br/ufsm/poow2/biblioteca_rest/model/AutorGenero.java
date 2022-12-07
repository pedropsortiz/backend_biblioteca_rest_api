package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "AutorGenero")
public class AutorGenero {

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    private Genero genero;

    @Id
    private Long id;

}
