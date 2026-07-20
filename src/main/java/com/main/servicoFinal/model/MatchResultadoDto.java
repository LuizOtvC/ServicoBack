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
@Table(name = "match_resultado")
public class MatchResultadoDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id", nullable = false)
    private ProjetoDto projetoId;

    @Column(nullable = false, name = "score_total")
    private Double scoreTotal;

    @Column(nullable = false, name = "score_servicos")
    private Double scoreServico;

    @Column(nullable = false, name = "score_orcamento")
    private Double scoreOrcamento;

    @Column(nullable = false, name = "score_historico")
    private Double scoreHistorico;

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

    public Double getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Double scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Double getScoreServico() {
        return scoreServico;
    }

    public void setScoreServico(Double scoreServico) {
        this.scoreServico = scoreServico;
    }

    public Double getScoreOrcamento() {
        return scoreOrcamento;
    }

    public void setScoreOrcamento(Double scoreOrcamento) {
        this.scoreOrcamento = scoreOrcamento;
    }

    public Double getScoreHistorico() {
        return scoreHistorico;
    }

    public void setScoreHistorico(Double scoreHistorico) {
        this.scoreHistorico = scoreHistorico;
    }

}
