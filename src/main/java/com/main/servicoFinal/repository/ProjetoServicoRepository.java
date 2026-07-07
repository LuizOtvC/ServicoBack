/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.ProjetoServicoDto;
import com.main.servicoFinal.model.ProjetoServicoIdDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mateus
 */
public interface ProjetoServicoRepository extends JpaRepository<ProjetoServicoDto, ProjetoServicoIdDto>{

    List<ProjetoServicoDto> findByProjetoId(Long projetoId);
    
    
}
