/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.MatchResultadoDto;
import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.ProjetoServicoDto;
import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.model.PropostaScoreDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.repository.MatchResultadoRepository;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.ProjetoServicoRepository;
import com.main.servicoFinal.repository.PropostaRepository;
import com.main.servicoFinal.repository.UserRepository;
import com.main.servicoFinal.repository.UsuarioServicoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mateus
 */
@Service
public class MatchService {

    @Autowired
    private MatchResultadoRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioServicoRepository usuarioServicoRepository;

    @Autowired
    private ProjetoServicoRepository projetoServicoRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    public void calcularMatch(Long usuarioId, Long projetoId, Double valorProposto) {

        User usuario = userRepository.getReferenceById(usuarioId);
        ProjetoDto projeto = projetoRepository.getReferenceById(projetoId);

        List<ProjetoServicoDto> servicosProjeto = projetoServicoRepository.findByProjetoId(projetoId);
        List<UsuarioServicoDto> servicosUsuario = usuarioServicoRepository.findByUsuarioId(usuarioId);

        double totalServicos = servicosProjeto.size();
        double pontuacao = 0.0;

        for (ProjetoServicoDto ps : servicosProjeto) {
            for (UsuarioServicoDto us : servicosUsuario) {
                if (us.getServico().getId().equals(ps.getServico().getId())) {
                    pontuacao += pesoNivel(us.getNivel());
                }
            }
        }

        double scoreSkills = totalServicos > 0 ? pontuacao / totalServicos : 0.0;

        double orcamentoProjeto = projeto.getOrcamento();
        double proporcao = valorProposto / orcamentoProjeto;

        double scoreOrcamento;
        if (proporcao < 0.5) {

            scoreOrcamento = proporcao * 0.5;
        } else if (proporcao <= 1.0) {

            scoreOrcamento = 1.0;
        } else {

            scoreOrcamento = orcamentoProjeto / valorProposto;
        }

        long projetosConcluidos = propostaRepository
                .countByUsuarioIdAndStatus(usuarioId, PropostaDto.Status.ACEITA);
        double scoreHistorico = Math.min(projetosConcluidos / 10.0, 1.0);

        double scoreTotal = (scoreSkills * 0.35)
                + (scoreOrcamento * 0.25)
                + (scoreHistorico * 0.20)
                + (usuario.getReputacao() / 5.0 * 0.10)
                + (usuario.getHorasSemana() != null && projeto.getHorasEstimadas() != null
                ? Math.min((double) usuario.getHorasSemana() / projeto.getHorasEstimadas(), 1.0) * 0.10
                : 0.0);

        MatchResultadoDto match = new MatchResultadoDto();
        match.setUsuarioId(usuario);
        match.setProjetoId(projeto);
        match.setScoreTotal(scoreTotal);
        match.setScoreServico(scoreSkills);
        match.setScoreOrcamento(scoreOrcamento);
        match.setScoreHistorico(scoreHistorico);
        matchRepository.save(match);
    }

    public List<PropostaScoreDto> listarPropostasComScore(Long projetoId) {
        List<PropostaDto> propostas = propostaRepository.findByProjetoId(projetoId);
        List<PropostaScoreDto> resultado = new ArrayList<>();

        for (PropostaDto p : propostas) {
            Optional<MatchResultadoDto> match = matchRepository
                    .findByUsuarioIdIdAndProjetoIdId(p.getUsuario().getId(), projetoId);

            PropostaScoreDto dto = new PropostaScoreDto();
            dto.setPropostaId(p.getId());
            dto.setNomeUsuario(p.getUsuario().getNome());
            dto.setValorProposto(p.getValorProposto());
            dto.setDescricao(p.getDescricao());
            dto.setStatus(p.getStatus().name());
            dto.setScoreTotal(match.isPresent() ? match.get().getScoreTotal() : 0.0);
            dto.setScoreServicos(match.isPresent() ? match.get().getScoreServico() : 0.0);
            dto.setScoreOrcamento(match.isPresent() ? match.get().getScoreOrcamento() : 0.0);
            dto.setScoreHistorico(match.isPresent() ? match.get().getScoreHistorico() : 0.0);
            dto.setUsuarioId(p.getUsuario().getId());
            resultado.add(dto);
        }

        resultado.sort((a, b) -> Double.compare(b.getScoreTotal(), a.getScoreTotal()));
        return resultado;
    }

    private double pesoNivel(UsuarioServicoDto.Nivel nivel) {
        return switch (nivel) {
            case BASICO ->
                0.5;
            case INTERMEDIARIO ->
                0.75;
            case AVANCADO ->
                1.0;
        };
    }

}
