package com.main.servicoFinal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "projeto_servico")
public class ProjetoServicoDto {

    @EmbeddedId
    private ProjetoServicoIdDto id = new ProjetoServicoIdDto();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("projetoId")
    @JoinColumn(name = "projeto_id")
    private ProjetoDto projeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("servicoId")
    @JoinColumn(name = "servico_id")
    private ServicoDto servico;

    public ProjetoServicoIdDto getId() {  
        return id; 
    }
    public void setId(ProjetoServicoIdDto id) { 
        this.id = id; 
    }

    public ProjetoDto getProjeto() { 
        return projeto; 
    }
    public void setProjeto(ProjetoDto projeto) { 
        this.projeto = projeto; 
    }

    public ServicoDto getServico() { 
        return servico; 
    }
    public void setServico(ServicoDto servico) {
        this.servico = servico;
    }
}