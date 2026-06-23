/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.controller;

import com.main.servicoFinal.model.UserLogar;
import com.main.servicoFinal.model.UserRegistro;
import com.main.servicoFinal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
   @PostMapping("/logar")
    public String Logar(@RequestBody UserLogar user){
        return service.logar(user.getEmail(), user.getSenha());
    }
    
    @PostMapping("registrar")
    public String registrar(@RequestBody UserRegistro user){
        return service.registrar(user);
    }
    
    
    
    
}
