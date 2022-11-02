package br.ufsm.poow2.biblioteca_rest.Model;

import java.sql.Date;

public class UsuarioLivro {

    private int idUsuarioLivro;
    private Date dataEmprestimoUsuarioLivro;
    private Date dataDevolucaoUsuarioLivro;
    private int idUsuario;
    private int idLivro;
    private StatusUsuarioLivro statusUsuarioLivro;

    public UsuarioLivro() {
    }

    public UsuarioLivro(int idUsuarioLivro, Date dataEmprestimoUsuarioLivro, Date dataDevolucaoUsuarioLivro, int idUsuario, int idLivro, StatusUsuarioLivro statusUsuarioLivro) {
        this.idUsuarioLivro = idUsuarioLivro;
        this.dataEmprestimoUsuarioLivro = dataEmprestimoUsuarioLivro;
        this.dataDevolucaoUsuarioLivro = dataDevolucaoUsuarioLivro;
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.statusUsuarioLivro = statusUsuarioLivro;
    }

    public int getIdUsuarioLivro() {
        return idUsuarioLivro;
    }

    public void setIdUsuarioLivro(int idUsuarioLivro) {
        this.idUsuarioLivro = idUsuarioLivro;
    }

    public Date getDataEmprestimoUsuarioLivro() {
        return dataEmprestimoUsuarioLivro;
    }

    public void setDataEmprestimoUsuarioLivro(Date dataEmprestimoUsuarioLivro) {
        this.dataEmprestimoUsuarioLivro = dataEmprestimoUsuarioLivro;
    }

    public Date getDataDevolucaoUsuarioLivro() {
        return dataDevolucaoUsuarioLivro;
    }

    public void setDataDevolucaoUsuarioLivro(Date dataDevolucaoUsuarioLivro) {
        this.dataDevolucaoUsuarioLivro = dataDevolucaoUsuarioLivro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public StatusUsuarioLivro getStatusUsuarioLivro() {
        return statusUsuarioLivro;
    }

    public void setStatusUsuarioLivro(StatusUsuarioLivro statusUsuarioLivro) {
        this.statusUsuarioLivro = statusUsuarioLivro;
    }

    public enum StatusUsuarioLivro { aprovacao, emprestado, atrasado }

}
