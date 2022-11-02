package br.ufsm.poow2.biblioteca_rest.Model;

public class LivroGenero {

    private int idLivro;
    private int idGenero;

    public LivroGenero() {
    }

    public LivroGenero(int idLivro, int idGenero) {
        this.idLivro = idLivro;
        this.idGenero = idGenero;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }
}
