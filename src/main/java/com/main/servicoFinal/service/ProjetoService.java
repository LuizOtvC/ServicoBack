/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.ProjetoResposta;
import com.main.servicoFinal.model.ProjetoServicoDto;
import com.main.servicoFinal.model.ProjetoServicoIdDto;
import com.main.servicoFinal.model.ProjetoUserDto;
import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.model.PropostaRespostaDto;
import com.main.servicoFinal.model.ServicoDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.ProjetoServicoRepository;
import com.main.servicoFinal.repository.PropostaRepository;
import com.main.servicoFinal.repository.ServiceRepository;
import com.main.servicoFinal.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Mateus
 */
@Service
public class ProjetoService {
    @Autowired
    private UserRepository user;
    
    @Autowired
    private ProjetoRepository projetoRepository;
    
    @Autowired
    private ProjetoServicoRepository projetoServicoRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private PropostaRepository propostaRepository;
    
    @Autowired
    private MensagemService mensagemService;
    
    @Autowired
    private PropostaService propostaService;
    
    public void criarProjeto(Long usuarioId, ProjetoUserDto dados) {
    ProjetoDto projeto = new ProjetoDto();
    projeto.setUsuarioId(user.getReferenceById(usuarioId));
    projeto.setTitulo(dados.getTitulo());
    projeto.setDescricao(dados.getDescricao());
    projeto.setOrcamento(dados.getOrcamento());
    projeto.setHorasEstimadas(dados.getHorasEstimadas());
    projeto.setCriadoEm(LocalDateTime.now());
    projeto.setStatus(ProjetoDto.Status.ABERTO);
    projeto.setScoreRisco(0);
    projetoRepository.save(projeto);
    mensagemService.ProjetoCriado(projeto);
    
    for (Long servicoId : dados.getServicosId()) {
        ProjetoServicoDto ps = new ProjetoServicoDto();
        ps.setId(new ProjetoServicoIdDto(projeto.getId(), servicoId));
        ps.setProjeto(projeto);
        ps.setServico(serviceRepository.getReferenceById(servicoId));
        projetoServicoRepository.save(ps);
    }
}
     
    public List<ProjetoDto> listarProjetos() {
        return projetoRepository.findAll();
    }

    public List<ProjetoResposta> listarProjetosFiltro(Long id) {
    List<ProjetoDto> projetos = projetoRepository.findByStatusAndUsuarioIdIdNot(ProjetoDto.Status.ABERTO, id);
    List<ProjetoResposta> resultado = new ArrayList<>();

    for (ProjetoDto p : projetos) {
        List<String> servicos = new ArrayList<>();
        List<ProjetoServicoDto> ps = projetoServicoRepository.findByProjetoId(p.getId());
        for (ProjetoServicoDto s : ps) {
            servicos.add(s.getServico().getNome());
        }
        resultado.add(new ProjetoResposta(
            p.getId(),
        p.getTitulo(),
        p.getDescricao(),
        p.getOrcamento(),
        p.getHorasEstimadas(),
        p.getStatus().name(),
        servicos,
        p.getUsuarioId().getId(),
        p.getScoreRisco(),
        p.getCriadoEm()
        
        ));
    }
    return resultado;
}
    
  public ProjetoResposta projetoPorId(Long id) {
    ProjetoDto p = projetoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

    List<String> servicos = new ArrayList<>();
    List<ProjetoServicoDto> ps = projetoServicoRepository.findByProjetoId(p.getId());
    for (ProjetoServicoDto s : ps) {
        servicos.add(s.getServico().getNome());
    }

    ProjetoResposta resposta = new ProjetoResposta(
        p.getId(),
        p.getTitulo(),
        p.getDescricao(),
        p.getOrcamento(),
        p.getHorasEstimadas(),
        p.getStatus().name(),
        servicos,
        p.getUsuarioId().getId(),
        p.getScoreRisco(),
        p.getCriadoEm()
    );

    Optional<PropostaDto> propostaAceitaOpt = propostaRepository.findByProjeto_IdAndStatus(id, PropostaDto.Status.ACEITA);

    if (propostaAceitaOpt.isPresent()) {
        PropostaDto prop = propostaAceitaOpt.get();
        PropostaRespostaDto dto = new PropostaRespostaDto();
        dto.setId(prop.getId());
        dto.setProjetoId(prop.getProjeto().getId());
        dto.setNomeProjeto(prop.getProjeto().getTitulo());
        dto.setUsuarioId(prop.getUsuario().getId());
        dto.setNomeUsuario(prop.getUsuario().getNome());
        dto.setValorProposto(prop.getValorProposto());
        dto.setDescricao(prop.getDescricao());
        dto.setStatus(prop.getStatus().name());
        dto.setEnviadoEm(prop.getEnviadoEm());
        resposta.setPropostaAceita(dto);
    }

    return resposta;
}
   public List<ProjetoResposta> listarProjetosUsuario(Long id) {
    List<ProjetoDto> projetos = projetoRepository.findByUsuarioIdId(id);
    List<ProjetoResposta> resultado = new ArrayList<>();

    for (ProjetoDto p : projetos) {
        List<String> servicos = new ArrayList<>();
        List<ProjetoServicoDto> ps = projetoServicoRepository.findByProjetoId(p.getId());
        for (ProjetoServicoDto s : ps) {
            servicos.add(s.getServico().getNome());
        }
        resultado.add(new ProjetoResposta(
            p.getId(),
        p.getTitulo(),
        p.getDescricao(),
        p.getOrcamento(),
        p.getHorasEstimadas(),
        p.getStatus().name(),
        servicos,
        p.getUsuarioId().getId(),
        p.getScoreRisco(),
        p.getCriadoEm()
        ));
    }
    return resultado;
}
   
   public void projetoEmAndamento(Long id, String token) {
    ProjetoDto projeto = projetoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    projeto.setStatus(ProjetoDto.Status.EM_ANDAMENTO);
    projetoRepository.save(projeto);
    mensagemService.ProjetoEmAndamento(projeto);
    
    PropostaDto proposta = propostaRepository
    .findByProjetoAndStatus(projeto, PropostaDto.Status.ACEITA)
    .orElseThrow(() -> new RuntimeException("Nenhuma proposta aceita encontrada"));
    mensagemService.ProjetoEmAndamentoProposta(proposta);
}
   public void projetoConcluido(Long id, String token) {
    ProjetoDto projeto = projetoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

    projeto.setStatus(ProjetoDto.Status.CONCLUIDO);
    projetoRepository.save(projeto);
    mensagemService.ProjetoConcluido(projeto);

    propostaRepository.findByProjetoAndStatus(projeto, PropostaDto.Status.ACEITA)
        .ifPresent(proposta -> mensagemService.ProjetoConcluidoProposta(proposta));
}
   
   public void projetoCancelado(Long id) {
    ProjetoDto projeto = projetoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    projeto.setStatus(ProjetoDto.Status.CANCELADO);
    projetoRepository.save(projeto);
    mensagemService.ProjetoCancelado(projeto);

    List<PropostaDto> propostas = propostaRepository.findByProjeto(projeto);
    for (PropostaDto proposta : propostas) {
        if (proposta.getStatus() == PropostaDto.Status.PENDENTE || proposta.getStatus() == PropostaDto.Status.ACEITA) {
            propostaService.cancelarProposta(proposta.getId());
        }
    }
}
}
     

