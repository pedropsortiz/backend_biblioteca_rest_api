package br.ufsm.poow2.biblioteca_rest.Model;

import java.sql.Date;

public class Autor {

    private int idAutor;
    private String nomeAutor;
    private Date dataNascAutor;
    private Date dataMorteAutor;
    private String descricaoAutor;
    private String fotoAutor;

    public Autor() {
    }

    public Autor(int idAutor, String nomeAutor, Date dataNascAutor, Date dataMorteAutor, String descricaoAutor, String fotoAutor) {
        this.idAutor = idAutor;
        this.nomeAutor = nomeAutor;
        this.dataNascAutor = dataNascAutor;
        this.dataMorteAutor = dataMorteAutor;
        this.descricaoAutor = descricaoAutor;
        this.fotoAutor = fotoAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Date getDataNascAutor() {
        return dataNascAutor;
    }

    public void setDataNascAutor(Date dataNascAutor) {
        this.dataNascAutor = dataNascAutor;
    }

    public Date getDataMorteAutor() {
        return dataMorteAutor;
    }

    public void setDataMorteAutor(Date dataMorteAutor) {
        this.dataMorteAutor = dataMorteAutor;
    }

    public String getDescricaoAutor() {
        return descricaoAutor;
    }

    public void setDescricaoAutor(String descricaoAutor) {
        this.descricaoAutor = descricaoAutor;
    }

    public String getFotoAutor() {
        return fotoAutor;
    }

    public void setFotoAutor(String fotoAutor) {
        this.fotoAutor = fotoAutor;
    }

    public boolean checarDataNascMorte(Date dataNascAutor, Date dataMorteAutor){
        if (dataMorteAutor.after(dataMorteAutor)){
            return true;
        }
        return false;
    }

}
