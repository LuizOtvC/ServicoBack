/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.MensagemDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mateus
 */
@Repository
public interface MensagemRepository extends JpaRepository<MensagemDto, Long> {

    public List<MensagemDto> findByUsuarioIdId(Long usuarioId);
    
}
