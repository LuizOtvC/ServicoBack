/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.User;
import com.main.servicoFinal.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mateus
 */
@Service
public class InativoService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void verificarAoIniciar() {
        verificarInatividade();
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void verificarInatividade() {
        LocalDateTime limite = LocalDateTime.now().minusDays(30);
        List<User> inativos = userRepository.findByUltimoLoginBeforeAndStatus(limite, User.Status.ATIVO);
        for (User u : inativos) {
            if (u.getReputacao() < 1.0) {
                u.setStatus(User.Status.ARQUIVADO);
            } else {
                u.setStatus(User.Status.INATIVO);
            }
            userRepository.save(u);
        }
    }
}
