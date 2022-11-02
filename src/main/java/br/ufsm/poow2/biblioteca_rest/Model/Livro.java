package br.ufsm.poow2.biblioteca_rest.Model;

public class Livro {

    private int idLivro;
    private String nomeLivro;
    private String descricaoLivro;
    private int idAutor;
    private String capaLivro;
    private String qntdTotalLivro;
    private String qntdEmUsoLivro;

    public Livro(int idLivro, String nomeLivro, String descricaoLivro, int idAutor, String capaLivro, String qntdTotalLivro, String qntdEmUsoLivro) {
        this.idLivro = idLivro;
        this.nomeLivro = nomeLivro;
        this.descricaoLivro = descricaoLivro;
        this.idAutor = idAutor;
        this.capaLivro = capaLivro;
        this.qntdTotalLivro = qntdTotalLivro;
        this.qntdEmUsoLivro = qntdEmUsoLivro;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getNomeLivro() {
        return nomeLivro;
    }

    public void setNomeLivro(String nomeLivro) {
        this.nomeLivro = nomeLivro;
    }

    public String getDescricaoLivro() {
        return descricaoLivro;
    }

    public void setDescricaoLivro(String descricaoLivro) {
        this.descricaoLivro = descricaoLivro;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getCapaLivro() {
        return capaLivro;
    }

    public void setCapaLivro(String capaLivro) {
        this.capaLivro = capaLivro;
    }

    public String getQntdTotalLivro() {
        return qntdTotalLivro;
    }

    public void setQntdTotalLivro(String qntdTotalLivro) {
        this.qntdTotalLivro = qntdTotalLivro;
    }

    public String getQntdEmUsoLivro() {
        return qntdEmUsoLivro;
    }

    public void setQntdEmUsoLivro(String qntdEmUsoLivro) {
        this.qntdEmUsoLivro = qntdEmUsoLivro;
    }
}
