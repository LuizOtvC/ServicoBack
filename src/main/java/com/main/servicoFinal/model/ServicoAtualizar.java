/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

/**
 *
 * @author Mateus
 */
public class ServicoAtualizar {
    private Long idAntigo;
    private Long idNovo;
    private UsuarioServicoDto.Nivel nivel;

    public ServicoAtualizar() {
    }

    public ServicoAtualizar(Long idAntigo, Long idNovo, UsuarioServicoDto.Nivel nivel) {
        this.idAntigo = idAntigo;
        this.idNovo = idNovo;
        this.nivel = nivel;
    }

    public Long getIdAntigo() {
        return idAntigo;
    }

    public void setIdAntigo(Long idAntigo) {
        this.idAntigo = idAntigo;
    }

    

    public Long getIdNovo() {
        return idNovo;
    }

    public void setIdNovo(Long idNovo) {
        this.idNovo = idNovo;
    }

    public UsuarioServicoDto.Nivel getNivel() {
        return nivel;
    }

    public void setNivel(UsuarioServicoDto.Nivel nivel) {
        this.nivel = nivel;
    }

    
    
    
}
