/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Mateus
 */
@Entity
@Table(name = "projeto")
public class ProjetoDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuarioId;
    @Column (nullable = false, length = 200)
    private String titulo;
    @Column (nullable = false)
    private String descricao;
    @Column (nullable = false)
    private Double orcamento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    
    @Column(nullable = false, name = "score_risco")
    private Integer scoreRisco;
    
    @Column(nullable = false, name = "criado_em")
    private LocalDateTime criadoEm;
    
    @ElementCollection(fetch = FetchType.EAGER)
@CollectionTable(
    name = "projeto_dia_trabalho",
    joinColumns = @JoinColumn(name = "projeto_id")
)
@Enumerated(EnumType.STRING)
@Column(name = "dia_semana")
private Set<DiaSemana> diasTrabalho = new HashSet<>();
    
    



    public enum Status {
        ABERTO, EM_ANDAMENTO, CONCLUIDO, CANCELADO
    }
    
    public enum DiaSemana {
    DOMINGO,
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO
}

    public ProjetoDto() {
    }

    public ProjetoDto(Long id, User usuarioId, String titulo, String descricao, Double orcamento, Status status, Integer scoreRisco, LocalDateTime criadoEm) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.orcamento = orcamento;
        this.status = status;
        this.scoreRisco = scoreRisco;
        this.criadoEm = criadoEm;
    }

    

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(User usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Double orcamento) {
        this.orcamento = orcamento;
    }

    

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getScoreRisco() {
        return scoreRisco;
    }

    public void setScoreRisco(Integer scoreRisco) {
        this.scoreRisco = scoreRisco;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Set<DiaSemana> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(Set<DiaSemana> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }

    
    
    
    
}
