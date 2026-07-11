/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

/**
 *
 * @author Mateus
 */
public class UserLogar {
    
   
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private String reputação;

    public UserLogar() {
    }

    public UserLogar(String nome, String senha, String email, String telefone, String reputação) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.reputação = reputação;
    }

    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getReputação() {
        return reputação;
    }

    public void setReputação(String reputação) {
        this.reputação = reputação;
    }

    
    
    
    
}
