/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.model.PropostaRespostaDto;
import com.main.servicoFinal.repository.MensagemRepository;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.PropostaRepository;
import com.main.servicoFinal.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    
    @Autowired
    private MensagemService mensagemService;

    public void criarProposta(Long usuarioId, Long projetoId, Double valorProposto, String descricao) {
        if (propostaRepository.existsByUsuarioIdAndProjetoId(usuarioId, projetoId)) {
        throw new IllegalStateException("Você já enviou uma proposta para este projeto.");
    }
        PropostaDto proposta = new PropostaDto();
        proposta.setUsuario(userRepository.getReferenceById(usuarioId));
        proposta.setProjeto(projetoRepository.getReferenceById(projetoId));
        proposta.setValorProposto(valorProposto);
        proposta.setDescricao(descricao);
        proposta.setStatus(PropostaDto.Status.PENDENTE);
        proposta.setEnviadoEm(LocalDateTime.now());
        propostaRepository.save(proposta);
        mensagemService.PropostaEnviada(proposta);
    }
    
    public List<PropostaRespostaDto> listarPropostasPendentes(Long usuarioId) {
    List<PropostaDto> propostas = propostaRepository.findByProjetoUsuarioIdIdAndStatus(usuarioId, PropostaDto.Status.PENDENTE);
    List<PropostaRespostaDto> resultado = new ArrayList<>();
    for (PropostaDto p : propostas) {
        resultado.add(new PropostaRespostaDto(
            p.getId(),
            p.getProjeto().getId(),
            p.getProjeto().getTitulo(),
            p.getUsuario().getId(),
            p.getUsuario().getNome(),
            p.getValorProposto(),
            p.getDescricao(),
            p.getStatus().name(),
            p.getEnviadoEm()
        ));
    }
    return resultado;
}
    
    public void aceitarProposta(Long id) {
    PropostaDto proposta = propostaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));
    proposta.setStatus(PropostaDto.Status.ACEITA);
    propostaRepository.save(proposta);
    mensagemService.PropostaAceita(proposta);
}
    
    public List<PropostaRespostaDto> listarPropostasUsuario(Long usuarioId) {
    List<PropostaDto> propostas = propostaRepository.findByUsuarioId(usuarioId);
    List<PropostaRespostaDto> resultado = new ArrayList<>();
    for (PropostaDto p : propostas) {
        resultado.add(new PropostaRespostaDto(
            p.getId(),
            p.getProjeto().getId(),
            p.getProjeto().getTitulo(),
            p.getUsuario().getId(),
            p.getUsuario().getNome(),
            p.getValorProposto(),
            p.getDescricao(),
            p.getStatus().name(),
            p.getEnviadoEm()
        ));
    }
    return resultado;
}
    
     public void cancelarProposta(Long id) {
    PropostaDto proposta = propostaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));
    proposta.setStatus(PropostaDto.Status.CANCELADA);
    propostaRepository.save(proposta);
    mensagemService.PropostaCancelada(proposta);
}
     
     public void RecusarProposta(Long id) {
    PropostaDto proposta = propostaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));
    proposta.setStatus(PropostaDto.Status.RECUSADA);
    propostaRepository.save(proposta);
    mensagemService.PropostaRecusada(proposta);
}
     public boolean existeProposta(Long usuarioId, Long projetoId) {
    return propostaRepository.existsByUsuarioIdAndProjetoId(usuarioId, projetoId);
}
    
}
