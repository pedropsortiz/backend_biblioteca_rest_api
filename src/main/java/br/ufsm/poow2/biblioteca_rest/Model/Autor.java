package br.ufsm.poow2.biblioteca_rest.Model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {

    private int idAutor;
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
