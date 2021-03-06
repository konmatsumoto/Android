package com.example.android6969.listatimes;

import java.io.Serializable;

/**
 * Created by android6969 on 12/08/17.
 */

public class Aluno implements Serializable {
    private Long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private String email;
    private Double nota;

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    private String caminhoFoto;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
    @Override
    public String toString() {
        return nome;
    }


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
