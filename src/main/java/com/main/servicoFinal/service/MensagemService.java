/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.MensagemDto;
import com.main.servicoFinal.model.MensagemRespostaDto;
import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.repository.MensagemRepository;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.PropostaRepository;
import com.main.servicoFinal.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mateus
 */
@Service
public class MensagemService {
    
    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjetoRepository projetoRepository;
    
    
   public void PropostaAceita(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(proposta.getUsuario());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Sua proposta foi aceita!!");
    mensagem.setStatus(MensagemDto.Status.ACEITA);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
   
    public void PropostaRecusada(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(proposta.getUsuario());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Sua proposta foi recusada");
    mensagem.setStatus(MensagemDto.Status.RECUSADA);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
    
    public void PropostaCancelada(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(proposta.getUsuario());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Sua proposta foi cancelada");
    mensagem.setStatus(MensagemDto.Status.CANCELADA);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
    
    public void ProjetoEmAndamento(ProjetoDto projeto) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(projeto.getUsuarioId());
    mensagem.setProjetoId(projeto);
    mensagem.setMensagem("Projeto esta em andamento");
    mensagem.setStatus(MensagemDto.Status.EM_ANDAMENTO);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
    
     public void ProjetoEmAndamentoProposta(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    if(proposta.getStatus() == PropostaDto.Status.ACEITA){
    mensagem.setUsuarioId(proposta.getUsuario());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Projeto esta em andamento");
    mensagem.setStatus(MensagemDto.Status.EM_ANDAMENTO);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem); 
     }
}
    
    public void PropostaEnviada(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(proposta.getUsuario());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Proposta enviada com sucesso");
    mensagem.setStatus(MensagemDto.Status.ENVIADA);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
    
    
    public void ProjetoConcluido(ProjetoDto projeto) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(projeto.getUsuarioId());
    mensagem.setProjetoId(projeto);
    mensagem.setMensagem("Projeto foi concluido");
    mensagem.setStatus(MensagemDto.Status.CONCLUIDO);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
    
    public void ProjetoConcluidoProposta(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    if(proposta.getStatus() == PropostaDto.Status.ACEITA){
    mensagem.setUsuarioId(proposta.getUsuario());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Projeto esta concluido");
    mensagem.setStatus(MensagemDto.Status.CONCLUIDO);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem); 
     }
    }
    
    public void ProjetoCriado(ProjetoDto projeto) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(projeto.getUsuarioId());
    mensagem.setProjetoId(projeto);
    mensagem.setMensagem("Projeto criado com succeso");
    mensagem.setStatus(MensagemDto.Status.CRIADO);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
     public void ProjetoCancelado(ProjetoDto projeto) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(projeto.getUsuarioId());
    mensagem.setProjetoId(projeto);
    mensagem.setMensagem("Projeto cancelado com sucesso");
    mensagem.setStatus(MensagemDto.Status.CANCELADO);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
     public void ProjetoCanceladoProposta(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    if(proposta.getStatus() == PropostaDto.Status.ACEITA){
    mensagem.setUsuarioId(proposta.getUsuario());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Este projeto foi cancelado");
    mensagem.setStatus(MensagemDto.Status.CANCELADO);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem); 
     }
    }
     
     
    
   public List<MensagemRespostaDto> listarMensagens(Long usuarioId) {
        List<MensagemDto> mensagens = mensagemRepository.findByUsuarioIdIdOrderByIdDesc(usuarioId);
        List<MensagemRespostaDto> resultado = new ArrayList<>();

        for (MensagemDto m : mensagens) {
            resultado.add(new MensagemRespostaDto(
                m.getId(),
                m.getProjetoId().getTitulo(),
                m.getProjetoId().getId(),
                m.getMensagem(),
                m.getStatus().name(),
                m.getEnviadoEm()
            ));
        }
        return resultado;
    }
   public void deletarMensagem(Long id) {
    mensagemRepository.deleteById(id);  
}
   
   public void novaProposta(PropostaDto proposta) {
    MensagemDto mensagem = new MensagemDto();
    mensagem.setUsuarioId(proposta.getProjeto().getUsuarioId());
    mensagem.setProjetoId(proposta.getProjeto());
    mensagem.setMensagem("Você recebeu uma nova proposta de " + proposta.getUsuario().getNome() + " para o projeto " + proposta.getProjeto().getTitulo());
    mensagem.setStatus(MensagemDto.Status.PROPOSTA);
    mensagem.setEnviadoEm(LocalDateTime.now());
    mensagemRepository.save(mensagem);
}
   
}
