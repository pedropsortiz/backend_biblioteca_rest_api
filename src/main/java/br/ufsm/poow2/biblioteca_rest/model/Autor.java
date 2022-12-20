package br.ufsm.poow2.biblioteca_rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private @NotNull @NotBlank String nomeAutor;

    @Column(name = "dataNascAutor")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private @NotNull @NotBlank Date dataNascAutor;

    @Column(name = "dataMorteAutor")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataMorteAutor;

    @Column(name = "descricaoAutor")
    private String descricaoAutor;

    @Column(name = "urlFotoAutor")
    private String urlFotoAutor;

}
