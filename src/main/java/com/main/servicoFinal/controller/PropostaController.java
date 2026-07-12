/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.PropostaEnvioDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.service.PropostaService;
import com.main.servicoFinal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mateus
 */
@RestController
@RequestMapping("/proposta")
public class PropostaController {
    
    
    @Autowired
    private PropostaService service;
    
    @Autowired
    private TokenService tokens;
    
    @PostMapping("/criar")
    public void CriarProposta( @RequestBody PropostaEnvioDto dados, @RequestHeader("Authorization") String auth){
        String token = auth.replace("Bearer ", "");
    User usertoken = tokens.extrairClaims(token);
    service.criarProposta(usertoken.getId(), dados.getProjetoId(), dados.getValorProposto(), dados.getDescricao());
    }
    
}
