package com.main.servicoFinal.model;

import java.time.LocalDateTime;
import java.util.List;

public class ProjetoResposta {

    private Long id;
    private String titulo;
    private String descricao;
    private Double orcamento;
    private Integer horasEstimadas;
    private String status;
    private List<String> servicos;
    private Long usuarioId;
    private Integer scoreRisco;
    private LocalDateTime criadoEm;

    public ProjetoResposta(Long id, String titulo, String descricao, Double orcamento,
                            Integer horasEstimadas, String status, List<String> servicos,
                            Long usuarioId, Integer scoreRisco, LocalDateTime criadoEm) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.orcamento = orcamento;
        this.horasEstimadas = horasEstimadas;
        this.status = status;
        this.servicos = servicos;
        this.usuarioId = usuarioId;
        this.scoreRisco = scoreRisco;
        this.criadoEm = criadoEm;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Double getOrcamento() { return orcamento; }
    public Integer getHorasEstimadas() { return horasEstimadas; }
    public String getStatus() { return status; }
    public List<String> getServicos() { return servicos; }
    public Long getUsuarioId() { return usuarioId; }
    public Integer getScoreRisco() { return scoreRisco; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
}