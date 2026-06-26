package com.main.servicoFinal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario_servico")
public class UsuarioServicoDto {

    @EmbeddedId
    private UsuarioServicoId id = new UsuarioServicoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("servicoId")
    @JoinColumn(name = "servico_id")
    private ServicoDto servico;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Nivel nivel;

    public enum Nivel {
        BASICO, INTERMEDIARIO, AVANCADO
    }

    public UsuarioServicoId getId() { return id; }
    public void setId(UsuarioServicoId id) { this.id = id; }

    public User getUsuario() {
        return usuario; 
    }
    
    public void setUsuario(User usuario) {
        this.usuario = usuario; 
    }

    public ServicoDto getServico() { return servico; }
    public void setServico(ServicoDto servico) { this.servico = servico; }

    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }
}