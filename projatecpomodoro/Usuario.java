package com.example.projatecpomodoro;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nome;
    private String usuario;
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "\nID: " + id +"\n"+
                "Nome: " + nome + "\n" +
                "Usuário: " + usuario + "\n" +
                "Senha: " + senha+"\n";
    }
}
