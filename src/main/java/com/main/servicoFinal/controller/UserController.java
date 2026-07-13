/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UserUpd;
import com.main.servicoFinal.model.UserPerfil;
import com.main.servicoFinal.model.UserRegistro;
import com.main.servicoFinal.service.TokenService;
import com.main.servicoFinal.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService service;
    
    @Autowired
    private TokenService tokens;
    
   @PostMapping("/logar")
    public String Logar(@RequestBody User user){
        return service.logar(user.getEmail(), user.getSenha());
    }
    
    @PostMapping("registrar")
    public String registrar(@RequestBody UserRegistro user){
        return service.registrar(user);
    }
    
    @PutMapping("/atualizar")
    public String atualizar(@RequestBody UserUpd user, @RequestHeader("Authorization") String auth){
        String token = auth.replace("Bearer ", "");
        User usertoken = tokens.extrairClaims(token);
        service.atualizarPerfil(usertoken.getId(), user);
        return "usuario atualizado com sucesso";
    }
    
    @GetMapping("/perfil")
    public UserPerfil ListarPerfil(@RequestHeader("Authorization") String auth){
        String token = auth.replace("Bearer ", "");
        User usertoken = tokens.extrairClaims(token);
        return service.verPerfil(usertoken.getId());
    }
    @GetMapping("/perfilId/{id}")
    public UserPerfil ListarPerfilId(@RequestHeader("Authorization") String auth,  @PathVariable Long id){
        String token = auth.replace("Bearer ", "");
        tokens.extrairClaims(token);
        return service.verPerfil(id);
    }
}
