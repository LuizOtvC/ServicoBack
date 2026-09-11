/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.ProjetoResposta;
import com.main.servicoFinal.model.ProjetoUserDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.service.MatchService;
import com.main.servicoFinal.service.ProjetoService;
import com.main.servicoFinal.service.ServicoService;
import com.main.servicoFinal.service.TokenService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/projeto")
public class ProjetoController {
    @Autowired
    private ProjetoService service;

    @Autowired
    private TokenService tokens;

    @Autowired
    private MatchService matchService;

    @PostMapping("/criar")
    public void criarProjeto(@RequestBody ProjetoUserDto dados) {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.criarProjeto(usuarioLogado.getId(), dados);
    }

    @GetMapping("/listar")
    public List<ProjetoDto> listarProjeto() {
        return service.listarProjetos();
    }

    @GetMapping("/listarFiltro")
    public Page<ProjetoResposta> listarComFiltro(@RequestParam(required = false) Double orcamentoMin, @RequestParam(required = false) List<Long> servicosIds, @RequestParam(required = false) List<String> diasSemana, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {

        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ProjetoDto.DiaSemana> dias = diasSemana != null ? diasSemana.stream().map(ProjetoDto.DiaSemana::valueOf).toList() : null;
        Pageable pageable = PageRequest.of(page, size);
        return service.listarProjetosComFiltro(usuarioLogado.getId(), orcamentoMin, servicosIds, dias, pageable);
    }

    @GetMapping("/listarId/{id}")
    public ProjetoResposta listarProjetoId(@PathVariable Long id) {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        matchService.calcularMatchProjeto(usuarioLogado.getId(), id);
        return service.projetoPorId(id);
    }

    @GetMapping("/listarFiltroUser")
    public List<ProjetoResposta> listarProjetosFiltroUsuario() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.listarProjetosUsuario(usuarioLogado.getId());
    }

    @PutMapping("/andamento/{id}")
    public void emAndamentoProjeto(@PathVariable Long id) {
        service.projetoEmAndamento(id);
    }

    @PutMapping("/concluido/{id}")
    public void ConcluidoProjeto(@PathVariable Long id) {
        service.projetoConcluido(id);
    }

    @PutMapping("/cancelar/{id}")
    public void CanceladoProjeto(@PathVariable Long id) {
        service.projetoCancelado(id);
    }

    @PutMapping("/arquivar/{id}")
    public void arquivarProjeto(@PathVariable Long id) {
        service.arquivarProjeto(id);
    }
}
