package com.main.servicoFinal.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @Column(name = "peso_servicos")
    private Double pesoServicos;

    @Column(name = "peso_orcamento")
    private Double pesoOrcamento;

    @Column(name = "peso_historico")
    private Double pesoHistorico;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioServicoDto> servicos = new ArrayList<>();

    @Column(length = 1000)
    private String descricao;

    @ElementCollection(fetch = FetchType.EAGER)
@CollectionTable(
        name = "usuario_dia_trabalho",
        joinColumns = @JoinColumn(name = "usuario_id")
)
@Enumerated(EnumType.STRING)
@Column(name = "dia_semana")
private Set<DiaSemana> diasTrabalho = new HashSet<>();

    public enum DiaSemana {
        DOMINGO, SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO
    }

    public User() {
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

    public List<UsuarioServicoDto> getServicos() {
        return servicos;
    }

    public void setServicos(List<UsuarioServicoDto> servicos) {
        this.servicos = servicos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<DiaSemana> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(Set<DiaSemana> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }

    

    
}
