/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.service.ServicoService;
import com.main.servicoFinal.service.TokenService;
import com.main.servicoFinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mateus
 */
@RestController
@RequestMapping("/servico")
public class ServiceController {
    @Autowired
    private ServicoService service; 
    
    @Autowired
    private TokenService tokens;
    
    @PostMapping("/{servicoId}/habilidades")
    public void adicionarHabilidade(@PathVariable Long servicoId,@RequestParam UsuarioServicoDto.Nivel nivel,@RequestHeader("Authorization") String auth) {

    String token = auth.replace("Bearer ", "");
    User usertoken = tokens.extrairClaims(token);

    service.adicionarHabilidade(usertoken.getId(), servicoId, nivel);
}
    
}
