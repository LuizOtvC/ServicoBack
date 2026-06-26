package com.main.servicoFinal.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioServicoId implements Serializable {

    private Long usuarioId;
    private Long servicoId;

    public UsuarioServicoId() {}

    public UsuarioServicoId(Long usuarioId, Long servicoId) {
        this.usuarioId = usuarioId;
        this.servicoId = servicoId;
    }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getServicoId() { return servicoId; }
    public void setServicoId(Long servicoId) { this.servicoId = servicoId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuarioServicoId)) return false;
        UsuarioServicoId that = (UsuarioServicoId) o;
        return Objects.equals(usuarioId, that.usuarioId) &&
               Objects.equals(servicoId, that.servicoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId, servicoId);
    }
}