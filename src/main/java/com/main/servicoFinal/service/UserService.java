/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UserUpd;
import com.main.servicoFinal.model.UserPerfil;
import com.main.servicoFinal.model.UserRegistro;
import com.main.servicoFinal.repository.UserRepository;
import java.util.Comparator;
import java.util.List;
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
                .orElseThrow(()
                        -> new ResponseStatusException(
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
        if (dados.getNome() == null || dados.getNome().equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "preencha o nome corretamente");
        }
        if (dados.getEmail() == null || dados.getEmail().equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "preencha o email corretamente");
        }
        if (dados.getSenha() == null || dados.getSenha().equals("") || !senhaValida(dados.getSenha())) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Preencha a senha corretamente, A senha deve ter no mínimo 6 caracteres, uma letra maiúscula e um caractere especial");
        }
        if (dados.getTelefone() == null || dados.getTelefone().equals("")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "preencha o telefone corretamente");
        }
        if (repository.existsByEmail(dados.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        if (repository.existsByTelefone(dados.getTelefone())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Telefone já cadastrado");
        }

        User user = new User();
        user.setNome(dados.getNome());
        user.setEmail(dados.getEmail());
        user.setSenha(dados.getSenha());
        user.setTelefone(dados.getTelefone());
        repository.save(user);
        return tokenrepository.gerarToken(user);
    }

    private boolean senhaValida(String senha) {
        String regex = "^(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\":{}|<>_\\-+=]).{6,}$";
        return senha.matches(regex);
    }

    public void atualizarPerfil(Long id, UserUpd dados) {
        User user = repository.getReferenceById(id);
        user.setNome(dados.getNome());
        user.setDiasTrabalho(dados.getDiasTrabalho());
        user.setDescricao(dados.getDescricao());
        user.setTelefone(dados.getTelefone());
        repository.save(user);
    }

    public UserPerfil verPerfil(Long id) {
        User user = repository.getReferenceById(id);

        List<String> dias = user.getDiasTrabalho()
        .stream()
        .sorted(Comparator.comparingInt(d -> switch (d) {
            case DOMINGO -> 1;
            case SEGUNDA -> 2;
            case TERCA -> 3;
            case QUARTA -> 4;
            case QUINTA -> 5;
            case SEXTA -> 6;
            case SABADO -> 7;
        }))
        .map(Enum::name)
        .toList();

        return new UserPerfil(
                user.getNome(),
                user.getDescricao(),
                user.getEmail(),
                user.getTelefone(),
                user.getReputacao(),
                user.getId(),
                dias
        );
    }

}
