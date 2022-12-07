package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "Autor")
public class Autor {

    @Id
    @SequenceGenerator(
            name = "autor_sequence",
            sequenceName = "autor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "autor_sequence"
    )
    @Column(
            name = "idAutor",
            updatable = false
    )
    private Long idAutor;

    @Column(
            name = "nomeAutor",
            nullable = false,
            columnDefinition = "VARCHAR (255)"
    )
    private @NotBlank String nomeAutor;

    @Column(
            name = "dataNascAutor",
            nullable = false,
            columnDefinition = "DATE"
    )
    private Date dataNascAutor;

    @Column(
            name = "dataMorteAutor",
            columnDefinition = "DATE"
    )
    private Date dataMorteAutor;

    @Column(
            name = "descricaoAutor",
            columnDefinition = "VARCHAR (255)"
    )
    private String descricaoAutor;

    @Column(
            name = "urlFotoAutor",
            columnDefinition = "VARCHAR (255)"
    )
    private String urlFotoAutor;

    public boolean checarDataNascMorte(Date dataNascAutor, Date dataMorteAutor){
        if (dataMorteAutor.after(dataMorteAutor)){
            return true;
        }
        return false;
    }

}
