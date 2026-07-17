/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.PropostaDto;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mateus
 */
@Repository
public interface PropostaRepository extends JpaRepository<PropostaDto, Long>{
List<PropostaDto> findByProjetoUsuarioIdIdAndStatus(Long usuarioId, PropostaDto.Status status);
List<PropostaDto> findByUsuarioId(Long usuarioId);
Optional<PropostaDto> findByProjeto_IdAndStatus(Long id, PropostaDto.Status status);
Optional<PropostaDto> findByProjetoAndStatus(ProjetoDto projeto,PropostaDto.Status status);
boolean existsByUsuarioIdAndProjetoId(Long usuarioId, Long projetoId);
    
}
