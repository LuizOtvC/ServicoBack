/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

import jakarta.persistence.Embeddable;
import java.util.Objects;

/**
 *
 * @author Mateus
 */
@Embeddable
public class ProjetoServicoIdDto {
    
    private Long projetoId;
    private Long servicoId;

    public ProjetoServicoIdDto() {}

    public ProjetoServicoIdDto(Long projetoId, Long servicoId) {
        this.projetoId = projetoId;
        this.servicoId = servicoId;
    }

    public Long getProjetoId() { return projetoId; }
    public void setProjetoId(Long projetoId) { this.projetoId = projetoId; }

    public Long getServicoId() { return servicoId; }
    public void setServicoId(Long servicoId) { this.servicoId = servicoId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjetoServicoIdDto)) return false;
        ProjetoServicoIdDto that = (ProjetoServicoIdDto) o;
        return Objects.equals(projetoId, that.projetoId) &&
               Objects.equals(servicoId, that.servicoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projetoId, servicoId);
    }
}
