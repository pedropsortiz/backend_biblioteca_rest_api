package br.ufsm.poow2.biblioteca_rest.model;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {

    @Id private Long idAutor;
    private String nomeAutor;
    private Date dataNascAutor;
    private Date dataMorteAutor;
    private String descricaoAutor;
    private String fotoAutor;

    public boolean checarDataNascMorte(Date dataNascAutor, Date dataMorteAutor){
        if (dataMorteAutor.after(dataMorteAutor)){
            return true;
        }
        return false;
    }

}
