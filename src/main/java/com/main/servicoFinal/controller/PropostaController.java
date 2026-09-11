/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.PropostaEnvioDto;
import com.main.servicoFinal.model.PropostaRespostaDto;
import com.main.servicoFinal.model.PropostaScoreDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.service.MatchService;
import com.main.servicoFinal.service.PropostaService;
import com.main.servicoFinal.service.TokenService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private MatchService serviceMatch;


    @PostMapping("/criar")
    public void CriarProposta(@RequestBody PropostaEnvioDto dados) {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.criarProposta(usuarioLogado.getId(), dados.getProjetoId(), dados.getValorProposto(), dados.getDescricao());
    }

    @GetMapping("/listarPendente")
    public List<PropostaRespostaDto> listarProjetoFiltro() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.listarPropostasPendentes(usuarioLogado.getId());
    }

    @PutMapping("/aceitar/{id}")
    public void aceitarProposta(@PathVariable Long id) {
        service.aceitarProposta(id);
    }

    @GetMapping("/listarPropostas")
    public List<PropostaRespostaDto> listarPropostas() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return service.listarPropostasUsuario(usuarioLogado.getId());
    }

    @PutMapping("/cancelar/{id}")
    public void cancelarProposta(@PathVariable Long id) {
        service.cancelarProposta(id);
    }

    @PutMapping("/recusar/{id}")
    public void recusarProposta(@PathVariable Long id) {
        service.RecusarProposta(id);
    }

    @GetMapping("/existe/{id}")
    public boolean existeProposta(@PathVariable Long id) {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.existeProposta(usuarioLogado.getId(), id);
    }

    @GetMapping("/propostas/{id}")
    public List<PropostaScoreDto> listarPropostasComScore(@PathVariable Long id) {
        return serviceMatch.listarPropostasComScore(id);
    }
}
