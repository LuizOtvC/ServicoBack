package com.main.servicoFinal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;
    
    @Column(nullable = false, unique = true, length = 150)
    private String telefone;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false)
    private Double reputacao = 5.0;

    @Column(name = "horas_semana")
    private Integer horasSemana;

    @Column(name = "peso_servicos")
    private Double pesoServicos;

    @Column(name = "peso_orcamento")
    private Double pesoOrcamento;

    @Column(name = "peso_historico")
    private Double pesoHistorico;

    public User() {
    }

    public User(Long id, String nome, String email, String telefone, String senha, Integer horasSemana, Double pesoServicos, Double pesoOrcamento, Double pesoHistorico) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.horasSemana = horasSemana;
        this.pesoServicos = pesoServicos;
        this.pesoOrcamento = pesoOrcamento;
        this.pesoHistorico = pesoHistorico;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getReputacao() {
        return reputacao;
    }

    public void setReputacao(Double reputacao) {
        this.reputacao = reputacao;
    }

    public Integer getHorasSemana() {
        return horasSemana;
    }

    public void setHorasSemana(Integer horasSemana) {
        this.horasSemana = horasSemana;
    }

    public Double getPesoServicos() {
        return pesoServicos;
    }

    public void setPesoServicos(Double pesoServicos) {
        this.pesoServicos = pesoServicos;
    }

    public Double getPesoOrcamento() {
        return pesoOrcamento;
    }

    public void setPesoOrcamento(Double pesoOrcamento) {
        this.pesoOrcamento = pesoOrcamento;
    }

    public Double getPesoHistorico() {
        return pesoHistorico;
    }

    public void setPesoHistorico(Double pesoHistorico) {
        this.pesoHistorico = pesoHistorico;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}