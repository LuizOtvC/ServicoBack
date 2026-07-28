/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.AvaliacaoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Aluno
 */
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoDto, Long> {

    boolean existsByProjetoIdAndAvaliadorId(Long projetoId, Long avaliadorId);

    List<AvaliacaoDto> findByAvaliadoId(Long avaliadoId);
}
