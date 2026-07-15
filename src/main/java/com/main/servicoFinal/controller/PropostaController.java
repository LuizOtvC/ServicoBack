/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.PropostaEnvioDto;
import com.main.servicoFinal.model.PropostaRespostaDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.service.PropostaService;
import com.main.servicoFinal.service.TokenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @GetMapping("/listarPendente")
public List<PropostaRespostaDto> listarProjetoFiltro(@RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    
    tokens.extrairClaims(token);
    User usertoken = tokens.extrairClaims(token);
   
    return service.listarPropostasPendentes(usertoken.getId());
}

    @PutMapping("/aceitar/{id}")
public void aceitarProposta(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    tokens.extrairClaims(token);
    service.aceitarProposta(id, token);
}

@GetMapping("/listarPropostas")
public List<PropostaRespostaDto> listarPropostas(@RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    
    tokens.extrairClaims(token);
    User usertoken = tokens.extrairClaims(token);
   
    return service.listarPropostasUsuario(usertoken.getId());
}
@PutMapping("/cancelar/{id}")
public void cancelarProposta(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    tokens.extrairClaims(token);
    service.cancelarProposta(id, token);
}

@PutMapping("/recusar/{id}")
public void recusarProposta(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    tokens.extrairClaims(token);
    service.RecusarProposta(id, token);
}
    
}
