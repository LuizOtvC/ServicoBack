/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.*;
import com.main.servicoFinal.service.TokenService;
import com.main.servicoFinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

/**
 *
 * @author Mateus
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokens;

    @PostMapping("/logar")
    public TokenResponseDto logar(@RequestBody User user) {
        return service.logar(user.getEmail(), user.getSenha());
    }

    @PostMapping("registrar")
    public String registrar(@RequestBody UserRegistro user) {
        return service.registrar(user);
    }

    @PutMapping("/atualizar")
    public String atualizar(@RequestBody UserUpd user) {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.atualizarPerfil(usuarioLogado.getId(), user);
        return "usuario atualizado com sucesso";
    }

    @GetMapping("/perfil")
    public UserPerfil ListarPerfil() {
        User usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return service.verPerfil(usuarioLogado.getId());

    }

    @GetMapping("/perfilId/{id}")
    public UserPerfil ListarPerfilId(@PathVariable Long id) {
        return service.verPerfil(id);
    }

    @PostMapping("/refresh")
    public TokenResponseDto refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");

        if (!tokens.validarToken(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Refresh token inválido ou expirado");
        }

        User usuario = tokens.extrairClaims(refreshToken);
        String novoAccessToken = tokens.gerarToken(usuario);
        String novoRefreshToken = tokens.gerarRefreshToken(usuario);

        return new TokenResponseDto(novoAccessToken, novoRefreshToken);
    }
}
