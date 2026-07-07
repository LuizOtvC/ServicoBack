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
import com.main.servicoFinal.model.ServicoDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.ProjetoServicoRepository;
import com.main.servicoFinal.repository.ServiceRepository;
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
public class ProjetoService {
    @Autowired
    private UserRepository user;
    
    @Autowired
    private ProjetoRepository projetoRepository;
    
    @Autowired
    private ProjetoServicoRepository projetoServicoRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
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

    public List<ProjetoResposta> listarProjetosFiltro() {
    List<ProjetoDto> projetos = projetoRepository.findByStatus(ProjetoDto.Status.ABERTO);
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
            servicos
        ));
    }
    return resultado;
}
    
}
     

