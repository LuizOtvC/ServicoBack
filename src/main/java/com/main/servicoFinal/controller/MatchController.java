/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.MatchResultadoDto;
import com.main.servicoFinal.model.PropostaScoreDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.repository.MatchResultadoRepository;
import com.main.servicoFinal.service.MatchService;
import com.main.servicoFinal.service.MensagemService;
import com.main.servicoFinal.service.TokenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mateus
 */
@RestController
@RequestMapping("match")
public class MatchController {

    @Autowired
    private MatchService service;

    @Autowired
    private MatchResultadoRepository repositoryMatch;

    @Autowired
    private TokenService tokens;

    @GetMapping("/score/{projetoId}")
    public Double getScoreProjeto(@PathVariable Long projetoId,@RequestHeader("Authorization") String auth) {String token = auth.replace("Bearer ", "");
        User usertoken = tokens.extrairClaims(token);
        service.calcularMatchProjeto(usertoken.getId(), projetoId);
        return repositoryMatch.findByUsuarioIdIdAndProjetoIdId(usertoken.getId(), projetoId).map(MatchResultadoDto::getScoreTotal).orElse(0.0);
    }

}
