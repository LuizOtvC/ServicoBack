/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.MensagemRespostaDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.service.MensagemService;
import com.main.servicoFinal.service.TokenService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mateus
 */
@RestController
@RequestMapping("/mensagem")
public class MensagemController {

    @Autowired
    private MensagemService service;

    @Autowired
    private TokenService tokens;

    @GetMapping("/listar")
    public List<MensagemRespostaDto> listarMensagens() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.listarMensagens(usuarioLogado.getId());
    }

    @DeleteMapping("/deletar/{id}")
    public void apagarmensagem(@PathVariable Long id) {
        service.deletarMensagem(id);
    }

    @PostMapping("/marcarLido")
    public void marcarComoLido() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.marcarComoLida(usuarioLogado.getId());
    }

    @GetMapping("/naoLidas")
    public Long contarNaoLidas() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.VerQuantasMensagensNaoLidas(usuarioLogado.getId());
    }

}
