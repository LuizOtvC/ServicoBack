/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.PropostaRepository;
import com.main.servicoFinal.repository.UserRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mateus
 */
@Service
public class PropostaService {
    
    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public void criarProposta(Long usuarioId, Long projetoId, Double valorProposto, String descricao) {
        PropostaDto proposta = new PropostaDto();
        proposta.setUsuario(userRepository.getReferenceById(usuarioId));
        proposta.setProjeto(projetoRepository.getReferenceById(projetoId));
        proposta.setValorProposto(valorProposto);
        proposta.setDescricao(descricao);
        proposta.setStatus(PropostaDto.Status.PENDENTE);
        proposta.setEnviadoEm(LocalDateTime.now());
        propostaRepository.save(proposta);
    }
    
}
