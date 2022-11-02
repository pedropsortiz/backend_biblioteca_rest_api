package br.ufsm.poow2.biblioteca_rest.Model;

public class Genero {

    private int idGenero;
    private String nomeGenero;

    public Genero() {
    }

    public Genero(int idGenero, String nomeGenero) {
        this.idGenero = idGenero;
        this.nomeGenero = nomeGenero;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

//    public boolean buscarGenero(Strr){
//
//    }

}
