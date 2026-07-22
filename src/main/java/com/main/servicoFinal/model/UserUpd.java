/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

import java.util.Set;

public class UserUpd {

    private String nome;
    private String descricao;
    private String telefone;
    private Set<User.DiaSemana> diasTrabalho;

    public UserUpd(String nome, String descricao, String telefone, Set<User.DiaSemana> diasTrabalho) {
        this.nome = nome;
        this.descricao = descricao;
        this.telefone = telefone;
        this.diasTrabalho = diasTrabalho;
    }

    public UserUpd() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<User.DiaSemana> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(Set<User.DiaSemana> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }
}
