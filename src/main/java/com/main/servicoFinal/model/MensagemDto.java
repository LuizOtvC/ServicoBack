/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author Mateus
 */
@Entity
@Table(name = "mensagem")
public class MensagemDto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuarioId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private ProjetoDto projetoId;
    
    @Column (nullable = false, length = 50)
    private String mensagem;  
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    
    @Column(nullable = false, name = "enviado_em")
    private LocalDateTime enviadoEm;
 

    public enum Status {
        ACEITA, RECUSADA, CANCELADA, EM_ANDAMENTO, CONCLUIDO, CANCELADO, CRIADO, ENVIADA, PROPOSTA
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

    public ProjetoDto getProjetoId() {
        return projetoId;
    }

    public void setProjetoId(ProjetoDto projetoId) {
        this.projetoId = projetoId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEnviadoEm() {
        return enviadoEm;
    }

    public void setEnviadoEm(LocalDateTime enviadoEm) {
        this.enviadoEm = enviadoEm;
    }
    
    
    
}
