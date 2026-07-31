/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.MatchDto;
import com.main.servicoFinal.model.PropostaDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mateus
 */
public interface MatchResultadoRepository extends JpaRepository<MatchDto, Long> {

    Optional<MatchDto> findByUsuarioIdIdAndProjetoIdId(Long usuarioId, Long projetoId);

    List<MatchDto> findByProjetoIdIdOrderByScoreTotalDesc(Long projetoId);
}
