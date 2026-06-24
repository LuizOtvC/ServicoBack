/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UserFree;
import com.main.servicoFinal.model.UserPerfil;
import com.main.servicoFinal.model.UserRegistro;
import com.main.servicoFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Mateus
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private TokenService tokenrepository;
    
    public String logar(String email, String senha) {
    User user = repository.findByEmail(email)
            .orElseThrow(() ->
                new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Email invalido"
            ));

    if (!senha.equals(user.getSenha())) {
        throw new ResponseStatusException(
            HttpStatus.UNAUTHORIZED,
            "senha inválido"
        );
    }

    return tokenrepository.gerarToken(user);
}
    
    public String registrar(UserRegistro dados) {
    if (repository.existsByEmail(dados.getEmail())) {
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Email já cadastrado");
    }else if(dados.getEmail().equals("")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(400),"prencha o email corretamente");
    }else if(dados.getNome().equals("")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(400),"prencha o nome corretamente");
    }else if(dados.getSenha().equals("")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(400),"prencha a senha corretamente");
    }else if(repository.existsByTelefone(dados.getTelefone())){
        throw new ResponseStatusException(HttpStatus.CONFLICT,"Telefone já cadastrado");
    }else if(dados.getTelefone().equals("")){
        throw new ResponseStatusException(HttpStatusCode.valueOf(400),"prencha o telefone corretamente");
    }

    User user = new User();
    user.setNome(dados.getNome());
    user.setEmail(dados.getEmail());
    user.setSenha(dados.getSenha());
    user.setTelefone(dados.getTelefone());

    repository.save(user);
    return tokenrepository.gerarToken(user);
}
    
    public void atualizarPerfil(Long id, UserFree dados) {
    User user = repository.getReferenceById(id);
    user.setNome(dados.getNome());
    user.setTelefone(dados.getTelefone());
    user.setHorasSemana(dados.getHorasSemana());
    repository.save(user);
}
    public UserPerfil verPerfil(Long id) {
    User user = repository.getReferenceById(id);
    
    return new UserPerfil(
        user.getNome(),
        user.getEmail(),
        user.getTelefone(),
        user.getReputacao(),
        user.getHorasSemana()
    );
}
    
}
