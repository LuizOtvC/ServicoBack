/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import ch.qos.logback.core.model.Model;
import com.main.servicoFinal.model.ServicoAtualizar;
import com.main.servicoFinal.model.ServicoDto;
import com.main.servicoFinal.model.ServicoListar;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UserPerfil;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.service.ServicoService;
import com.main.servicoFinal.service.TokenService;
import com.main.servicoFinal.service.UserService;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public void adicionarHabilidade(@PathVariable Long servicoId, @RequestParam UsuarioServicoDto.Nivel nivel) {

        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        service.adicionarHabilidade(usuarioLogado.getId(), servicoId, nivel);
    }

    @GetMapping("/listar")
    public List<ServicoDto> listar() {

        return service.listarTodosServicos();

    }

    @GetMapping("/listarHabilidadesId")
    public List<ServicoListar> listarHabilidadesId() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.listarHabilidades(usuarioLogado.getId());
    }

    @PutMapping("/atualizar")
    public void atualizarHabilidade(@RequestBody ServicoAtualizar dados) {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.atualizarHabilidade(usuarioLogado.getId(), dados);
    }

    @DeleteMapping("/deletar")
    public void apagarHabilidade(@RequestBody ServicoAtualizar dados) {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.deletarHabilidade(usuarioLogado.getId(), dados.getIdAntigo());
    }

    @GetMapping("/listarHabilidadesId/{id}")
    public List<ServicoListar> listarHabilidadesPorUsuario(@PathVariable Long id) {
        return service.listarHabilidades(id);
    }

}
