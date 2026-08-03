/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.MatchDto;
import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.ProjetoServicoDto;
import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.model.PropostaScoreDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.User.DiaSemana;
import static com.main.servicoFinal.model.User.DiaSemana.DOMINGO;
import static com.main.servicoFinal.model.User.DiaSemana.QUARTA;
import static com.main.servicoFinal.model.User.DiaSemana.QUINTA;
import static com.main.servicoFinal.model.User.DiaSemana.SABADO;
import static com.main.servicoFinal.model.User.DiaSemana.SEGUNDA;
import static com.main.servicoFinal.model.User.DiaSemana.SEXTA;
import static com.main.servicoFinal.model.User.DiaSemana.TERCA;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.repository.MatchResultadoRepository;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.ProjetoServicoRepository;
import com.main.servicoFinal.repository.PropostaRepository;
import com.main.servicoFinal.repository.UserRepository;
import com.main.servicoFinal.repository.UsuarioServicoRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    Set<ProjetoDto.DiaSemana> diasProjeto = projeto.getDiasTrabalho();
    Set<DiaSemana> diasUsuario = usuario.getDiasTrabalho();
    double scoreDias;
    if (diasProjeto.isEmpty()) {
        scoreDias = 1.0;
    } else {
        long diasEmComum = diasProjeto.stream()
                .filter(diasUsuario::contains)
                .count();
        if (diasEmComum == 0) {
            scoreDias = 0.0;
        } else {
            scoreDias = (double) diasEmComum / diasProjeto.size();
        }
    }
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
    double scoreOrcamento;

if (projeto.getOrcamento() == 0) {
    scoreOrcamento = 1.0;
} else {
    double orcamentoProjeto = projeto.getOrcamento();
    double proporcao = valorProposto / orcamentoProjeto;
    
    if (valorProposto == null) {
    valorProposto = 0.0;
}

    if (proporcao < 0.5) {
        scoreOrcamento = proporcao * 0.5;
    } else if (proporcao <= 1.0) {
        scoreOrcamento = 1.0;
    } else {
        scoreOrcamento = orcamentoProjeto / valorProposto;
    }
}
    long projetosConcluidos = propostaRepository.countByUsuarioIdAndProjetoStatus(usuarioId, ProjetoDto.Status.CONCLUIDO);
    double scoreHistorico = Math.min(projetosConcluidos / 10.0, 1.0);
    double scoreTotal
        = (scoreSkills * 0.33)
        + (scoreOrcamento * 0.22)
        + (scoreHistorico * 0.05)
        + (usuario.getReputacao() / 5.0 * 0.12)
        + (scoreDias * 0.28);

    MatchDto match = matchRepository.findByUsuarioIdIdAndProjetoIdId(usuarioId, projetoId).orElse(new MatchDto());
    match.setUsuarioId(usuario);
    match.setProjetoId(projeto);
    match.setScoreTotal(scoreTotal);
    match.setScoreServico(scoreSkills);
    match.setScoreOrcamento(scoreOrcamento);
    match.setScoreHistorico(scoreHistorico);
    matchRepository.save(match);
}

    public void calcularMatchProjeto(Long usuarioId, Long projetoId) {
        User usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        ProjetoDto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Set<ProjetoDto.DiaSemana> diasProjeto = projeto.getDiasTrabalho();
        Set<DiaSemana> diasUsuario = usuario.getDiasTrabalho();
        double scoreDias;
        if (diasProjeto.isEmpty()) {
            scoreDias = 1.0;
        } else {
            long diasEmComum = diasProjeto.stream()
                    .filter(diasUsuario::contains)
                    .count();
            scoreDias = diasEmComum > 0 ? 1.0 : 0.0;
        }

        List<ProjetoServicoDto> servicosProjeto = projetoServicoRepository.findByProjetoId(projetoId);
        List<UsuarioServicoDto> servicosUsuario = usuarioServicoRepository.findByUsuarioId(usuarioId);
        boolean temAlgumServico = servicosProjeto.stream()
                .anyMatch(ps -> servicosUsuario.stream()
                .anyMatch(us -> us.getServico().getId().equals(ps.getServico().getId())));
        double scoreSkills = temAlgumServico ? 1.0 : 0.0;

        long projetosConcluidos = propostaRepository
                .countByUsuarioIdAndProjetoStatus(usuarioId, ProjetoDto.Status.CONCLUIDO);
        double scoreHistorico = Math.min(projetosConcluidos / 10.0, 1.0);

        double scoreReputacao = usuario.getReputacao() / 5.0;

        double scoreTotal = (scoreSkills * 0.35)
                + (scoreDias * 0.30)
                + (scoreHistorico * 0.20)
                + (scoreReputacao * 0.15);

        Optional<MatchDto> existente = matchRepository.findByUsuarioIdIdAndProjetoIdId(usuarioId, projetoId);

        MatchDto match = existente.orElse(new MatchDto());
        match.setUsuarioId(usuario);
        match.setProjetoId(projeto);
        match.setScoreTotal(scoreTotal);
        match.setScoreServico(scoreSkills);
        match.setScoreOrcamento(0.0);
        match.setScoreHistorico(scoreHistorico);
        matchRepository.save(match);
    }

    public List<PropostaScoreDto> listarPropostasComScore(Long projetoId) {
        List<PropostaDto> propostas = propostaRepository.findByProjetoId(projetoId);
        List<PropostaScoreDto> resultado = new ArrayList<>();

        for (PropostaDto p : propostas) {
            Optional<MatchDto> match = matchRepository
                    .findByUsuarioIdIdAndProjetoIdId(p.getUsuario().getId(), projetoId);

            List<String> dias = p.getUsuario().getDiasTrabalho()
                    .stream()
                    .sorted(Comparator.comparingInt(d -> switch (d) {
                case DOMINGO ->
                    1;
                case SEGUNDA ->
                    2;
                case TERCA ->
                    3;
                case QUARTA ->
                    4;
                case QUINTA ->
                    5;
                case SEXTA ->
                    6;
                case SABADO ->
                    7;
            }))
                    .map(d -> switch (d) {
                case DOMINGO ->
                    "DOMINGO";
                case SEGUNDA ->
                    "SEGUNDA";
                case TERCA ->
                    "TERÇA";
                case QUARTA ->
                    "QUARTA";
                case QUINTA ->
                    "QUINTA";
                case SEXTA ->
                    "SEXTA";
                case SABADO ->
                    "SÁBADO";
            })
                    .toList();
            PropostaScoreDto dto = new PropostaScoreDto();
            dto.setPropostaId(p.getId());
            dto.setNomeUsuario(p.getUsuario().getNome());
            dto.setValorProposto(p.getValorProposto());
            dto.setDescricao(p.getDescricao());
            dto.setDiasTrabalho(dias);
            dto.setStatus(p.getStatus().name());
            dto.setScoreTotal(match.isPresent() ? match.get().getScoreTotal() : 0.0);
            dto.setScoreServicos(match.isPresent() ? match.get().getScoreServico() : 0.0);
            dto.setScoreOrcamento(match.isPresent() ? match.get().getScoreOrcamento() : 0.0);
            dto.setScoreHistorico(match.isPresent() ? match.get().getScoreHistorico() : 0.0);
            dto.setUsuarioId(p.getUsuario().getId());

            resultado.add(dto);
            List<PropostaDto> propostasAceitas = propostaRepository.findByUsuarioIdAndStatus(p.getUsuario().getId(), PropostaDto.Status.ACEITA);

            Set<ProjetoDto.DiaSemana> diasProjeto = projetoRepository
                    .getReferenceById(projetoId).getDiasTrabalho();

            boolean temConflito = propostasAceitas.stream()
                    .anyMatch(aceita -> aceita.getProjeto().getDiasTrabalho().stream()
                    .anyMatch(diasProjeto::contains));

            dto.setTemConflitoDias(temConflito);
        }

        resultado.sort((a, b) -> Double.compare(b.getScoreTotal(), a.getScoreTotal()));
        return resultado;
    }

    public List<PropostaScoreDto> listarProjetoscomScore(Long projetoId) {
        List<PropostaDto> propostas = propostaRepository.findByProjetoId(projetoId);
        List<PropostaScoreDto> resultado = new ArrayList<>();

        for (PropostaDto p : propostas) {
            Optional<MatchDto> match = matchRepository.findByUsuarioIdIdAndProjetoIdId(p.getUsuario().getId(), projetoId);

            List<String> dias = p.getUsuario().getDiasTrabalho()
                    .stream()
                    .sorted(Comparator.comparingInt(d -> switch (d) {
                case DOMINGO ->
                    1;
                case SEGUNDA ->
                    2;
                case TERCA ->
                    3;
                case QUARTA ->
                    4;
                case QUINTA ->
                    5;
                case SEXTA ->
                    6;
                case SABADO ->
                    7;
            }))
                    .map(d -> switch (d) {
                case DOMINGO ->
                    "DOMINGO";
                case SEGUNDA ->
                    "SEGUNDA";
                case TERCA ->
                    "TERÇA";
                case QUARTA ->
                    "QUARTA";
                case QUINTA ->
                    "QUINTA";
                case SEXTA ->
                    "SEXTA";
                case SABADO ->
                    "SÁBADO";
            })
                    .toList();
            PropostaScoreDto dto = new PropostaScoreDto();
            dto.setPropostaId(p.getId());
            dto.setNomeUsuario(p.getUsuario().getNome());
            dto.setValorProposto(p.getValorProposto());
            dto.setDescricao(p.getDescricao());
            dto.setDiasTrabalho(dias);
            dto.setStatus(p.getStatus().name());
            dto.setScoreTotal(match.isPresent() ? match.get().getScoreTotal() : 0.0);
            dto.setScoreServicos(match.isPresent() ? match.get().getScoreServico() : 0.0);
            dto.setScoreOrcamento(match.isPresent() ? match.get().getScoreOrcamento() : 0.0);
            dto.setScoreHistorico(match.isPresent() ? match.get().getScoreHistorico() : 0.0);
            dto.setUsuarioId(p.getUsuario().getId());

            resultado.add(dto);
            List<PropostaDto> propostasAceitas = propostaRepository.findByUsuarioIdAndStatus(p.getUsuario().getId(), PropostaDto.Status.ACEITA);

            Set<ProjetoDto.DiaSemana> diasProjeto = projetoRepository
                    .getReferenceById(projetoId).getDiasTrabalho();

            boolean temConflito = propostasAceitas.stream()
                    .anyMatch(aceita -> aceita.getProjeto().getDiasTrabalho().stream()
                    .anyMatch(diasProjeto::contains));

            dto.setTemConflitoDias(temConflito);
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
