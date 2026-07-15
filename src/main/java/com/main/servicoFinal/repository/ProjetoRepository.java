/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.ProjetoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mateus
 */
public interface ProjetoRepository extends JpaRepository<ProjetoDto, Long> {
    List<ProjetoDto> findByStatusAndUsuarioIdIdNot(ProjetoDto.Status status, Long usuarioId);
    List<ProjetoDto> findByStatusAndUsuarioIdId(ProjetoDto.Status status, Long usuarioId);
    
}
