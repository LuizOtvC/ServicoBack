package com.main.servicoFinal.model;

import java.util.List;
import java.util.Set;

public class UserPerfil {

    private String nome;
    private String descricao;
    private String email;
    private String telefone;
    private Double reputacao;
    private Long id;
    private List<String> diasTrabalho;

    public UserPerfil() {
    }

    public UserPerfil(String nome, String descricao, String email, String telefone, Double reputacao, Long id, List<String> diasTrabalho) {
        this.nome = nome;
        this.descricao = descricao;
        this.email = email;
        this.telefone = telefone;
        this.reputacao = reputacao;
        this.id = id;
        this.diasTrabalho = diasTrabalho;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Double getReputacao() {
        return reputacao;
    }

    public void setReputacao(Double reputacao) {
        this.reputacao = reputacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(List<String> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }

    
}
