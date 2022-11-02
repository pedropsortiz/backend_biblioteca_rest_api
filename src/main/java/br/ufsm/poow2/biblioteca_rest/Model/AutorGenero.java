package br.ufsm.poow2.biblioteca_rest.Model;

public class AutorGenero {

    private int idAutor;
    private int idGenero;

    public AutorGenero() {
    }

    public AutorGenero(int idAutor, int idGenero) {
        this.idAutor = idAutor;
        this.idGenero = idGenero;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
}
