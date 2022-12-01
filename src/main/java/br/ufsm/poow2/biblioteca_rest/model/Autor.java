package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    @Column(name = "nomeAutor")
    private @NotBlank String nomeAutor;

    @Column(name = "nomeAutor")
    private @NotBlank Date dataNascAutor;

    @Column(name = "dataMorteAutor")
    private Date dataMorteAutor;

    @Column(name = "descricaoAutor")
    private String descricaoAutor;

    @Column(name = "urlFotoAutor")
    private String urlFotoAutor;
    @javax.persistence.Id
    private Long id;

    public boolean checarDataNascMorte(Date dataNascAutor, Date dataMorteAutor){
        if (dataMorteAutor.after(dataMorteAutor)){
            return true;
        }
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
