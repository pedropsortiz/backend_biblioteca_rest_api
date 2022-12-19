package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    @Column(name = "nomeAutor")
    private @NotBlank String nomeAutor;

    @Column(name = "dataNascAutor")
    private @NotBlank Date dataNascAutor;

    @Column(name = "dataMorteAutor")
    private Date dataMorteAutor;

    @Column(name = "descricaoAutor")
    private String descricaoAutor;

    @Column(name = "urlFotoAutor")
    private String urlFotoAutor;

}
