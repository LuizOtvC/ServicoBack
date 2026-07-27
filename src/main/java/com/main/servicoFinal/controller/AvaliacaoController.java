/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.User;
import com.main.servicoFinal.service.AvaliacaoService;
import com.main.servicoFinal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aluno
 */
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService service;

    @Autowired
    private TokenService tokens;

    @PostMapping("/criar/{id}")
    public void avaliar(@PathVariable Long id, @RequestParam Double nota, @RequestParam(required = false) String comentario, @RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        User usertoken = tokens.extrairClaims(token);
        service.avaliar(usertoken.getId(), id, nota, comentario);
    }

    @GetMapping("/jaAvaliei/{id}")
    public boolean jaAvaliei(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
        String token = auth.replace("Bearer ", "");
        User usertoken = tokens.extrairClaims(token);
        return service.jaAvaliou(id, usertoken.getId());
    }
}
