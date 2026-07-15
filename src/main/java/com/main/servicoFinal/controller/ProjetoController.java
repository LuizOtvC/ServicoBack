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
import com.main.servicoFinal.service.ProjetoService;
import com.main.servicoFinal.service.ServicoService;
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
    
    @PostMapping("/criar")
    public void criarProjeto(@RequestBody ProjetoUserDto dados, @RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    User usertoken = tokens.extrairClaims(token);
    service.criarProjeto(usertoken.getId(), dados);
}

@GetMapping("/listar")
public List<ProjetoDto> listarProjeto(@RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    tokens.extrairClaims(token);
    return service.listarProjetos();
}
@GetMapping("/listarFiltro")
public List<ProjetoResposta> listarProjetosFiltro(@RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    User usertoken = tokens.extrairClaims(token);
    return service.listarProjetosFiltro(usertoken.getId());
}
@GetMapping("/listarId/{id}")
public ProjetoResposta listarProjetoId(@RequestHeader("Authorization") String auth, @PathVariable Long id) {
    String token = auth.replace("Bearer ", "");
    tokens.extrairClaims(token);
    return service.projetoPorId(id);
}
@GetMapping("/listarFiltroUser")
public List<ProjetoResposta> listarProjetosFiltroUsuario(@RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    User usertoken = tokens.extrairClaims(token);
    return service.listarProjetosUsuario(usertoken.getId());
}
@PutMapping("/andamento/{id}")
public void emAndamentoProjeto(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    tokens.extrairClaims(token);
    service.projetoEmAndamento(id, token);
}

@PutMapping("/concluido/{id}")
public void ConcluidoProjeto(@PathVariable Long id, @RequestHeader("Authorization") String auth) {
    String token = auth.replace("Bearer ", "");
    tokens.extrairClaims(token);
    service.projetoConcluido(id, token);
}
}
