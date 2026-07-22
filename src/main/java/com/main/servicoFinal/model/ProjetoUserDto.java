/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Mateus
 */
public class ProjetoUserDto {
    
    private String titulo;
    private String descricao;
    private Double orcamento;
    private ProjetoDto.Status status;
    private List<Long> servicosId;
    private Set<ProjetoDto.DiaSemana> diasTrabalho;

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getOrcamento() { return orcamento; }
    public void setOrcamento(Double orcamento) { this.orcamento = orcamento; }

    public List<Long> getServicosId() { return servicosId; }
    public void setServicosId(List<Long> servicosId) { this.servicosId = servicosId; }

    public ProjetoDto.Status getStatus() {
        return status;
    }

    public void setStatus(ProjetoDto.Status status) {
        this.status = status;
    }

    public Set<ProjetoDto.DiaSemana> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(Set<ProjetoDto.DiaSemana> diasTrabalho) {
        this.diasTrabalho = diasTrabalho;
    }
    
    
    
}
