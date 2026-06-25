/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Aluno
 */
//@Entity
//@Table(name = "usuario_servico")
//public class UsuarioServicoDto {
//    
//    @Id
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
//    private User userId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "servico", referencedColumnName = "id", nullable = false)
//    private ServicoDto servicoId;
//
//    @Column(nullable = false, length = 150)
//    private String nivel;
//
//    public User getUserId() {
//        return userId;
//    }
//
//    public void setUserId(User userId) {
//        this.userId = userId;
//    }
//
//    public ServicoDto getServicoId() {
//        return servicoId;
//    }
//
//    public void setServicoId(ServicoDto servicoId) {
//        this.servicoId = servicoId;
//    }
//
//
//    public String getNivel() {
//        return nivel;
//    }
//
//    public void setNivel(String nivel) {
//        this.nivel = nivel;
//    }
//    
//    
//    
//    
//}
