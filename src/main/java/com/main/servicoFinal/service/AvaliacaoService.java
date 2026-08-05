/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.AvaliacaoDto;
import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.repository.AvaliacaoRepository;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.PropostaRepository;
import com.main.servicoFinal.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aluno
 */
@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private UserRepository userRepository;

    public void avaliar(Long avaliadorId, Long projetoId, Double nota, String comentario) {
        if (nota == null || nota < 0 || nota > 5) {
            throw new RuntimeException("A nota deve estar entre 0 e 5.");
        }

        ProjetoDto projeto = projetoRepository.findById(projetoId)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        if (projeto.getStatus() != ProjetoDto.Status.CONCLUIDO) {
            throw new RuntimeException("Só é possível avaliar projetos concluídos.");
        }

        PropostaDto propostaAceita = propostaRepository
                .findByProjetoIdAndStatus(projetoId, PropostaDto.Status.CONCLUIDA)
                .orElseThrow(() -> new RuntimeException("Nenhum profissional selecionado para este projeto."));

        Long donoId = projeto.getUsuarioId().getId();
        Long profissionalId = propostaAceita.getUsuario().getId();

        Long avaliadoId;
        if (avaliadorId.equals(donoId)) {
            avaliadoId = profissionalId;
        } else if (avaliadorId.equals(profissionalId)) {
            avaliadoId = donoId;
        } else {
            throw new RuntimeException("Você não participou deste projeto.");
        }

        if (avaliacaoRepository.existsByProjetoIdAndAvaliadorId(projetoId, avaliadorId)) {
            throw new RuntimeException("Você já avaliou este projeto.");
        }

        AvaliacaoDto avaliacao = new AvaliacaoDto();
        avaliacao.setProjeto(projeto);
        avaliacao.setAvaliador(userRepository.getReferenceById(avaliadorId));
        avaliacao.setAvaliado(userRepository.getReferenceById(avaliadoId));
        avaliacao.setNota(nota);
        avaliacao.setComentario(comentario);
        avaliacaoRepository.save(avaliacao);

        atualizarReputacao(avaliadoId);
    }

    public boolean jaAvaliou(Long projetoId, Long avaliadorId) {
        return avaliacaoRepository.existsByProjetoIdAndAvaliadorId(projetoId, avaliadorId);
    }

    private void atualizarReputacao(Long usuarioId) {
        List<AvaliacaoDto> avaliacoes = avaliacaoRepository.findByAvaliadoId(usuarioId);
        double media = avaliacoes.stream()
                .mapToDouble(AvaliacaoDto::getNota)
                .average()
                .orElse(5.0);

        User user = userRepository.getReferenceById(usuarioId);
        user.setReputacao(media);
        if (media < 2.0) {
        user.setStatus(User.Status.ARQUIVADO);
    } 
        userRepository.save(user);
    }
}
