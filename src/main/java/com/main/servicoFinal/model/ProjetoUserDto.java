/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

import java.util.List;

/**
 *
 * @author Mateus
 */
public class ProjetoUserDto {
    
    private String titulo;
    private String descricao;
    private Double orcamento;
    private Integer horasEstimadas;
    private ProjetoDto.Status status;
    private List<Long> servicosId;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getOrcamento() { return orcamento; }
    public void setOrcamento(Double orcamento) { this.orcamento = orcamento; }

    public Integer getHorasEstimadas() { return horasEstimadas; }
    public void setHorasEstimadas(Integer horasEstimadas) { this.horasEstimadas = horasEstimadas; }

    public List<Long> getServicosId() { return servicosId; }
    public void setServicosId(List<Long> servicosId) { this.servicosId = servicosId; }

    public ProjetoDto.Status getStatus() {
        return status;
    }

    public void setStatus(ProjetoDto.Status status) {
        this.status = status;
    }
    
    
    
}
