/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.ProjetoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Mateus
 */

public interface ProjetoRepository extends JpaRepository<ProjetoDto, Long> {
    List<ProjetoDto> findByStatusAndUsuarioIdIdNot(ProjetoDto.Status status, Long usuarioId);
    List<ProjetoDto> findByUsuarioIdId(Long usuarioId);
    
   @Query("""
    SELECT DISTINCT p FROM ProjetoDto p
    LEFT JOIN ProjetoServicoDto ps ON ps.projeto = p
    WHERE p.status = 'ABERTO'
    AND p.usuarioId.id <> :usuarioId
    AND (:orcamentoMin IS NULL OR p.orcamento >= :orcamentoMin)
    AND (:servicosIds IS NULL OR ps.servico.id IN :servicosIds)
    AND (:diasSemana IS NULL OR EXISTS (
        SELECT d FROM ProjetoDto pd JOIN pd.diasTrabalho d
        WHERE pd = p AND d IN :diasSemana
    ))
""")
List<ProjetoDto> findComFiltros(
    @Param("usuarioId") Long usuarioId,
    @Param("orcamentoMin") Double orcamentoMin,
    @Param("servicosIds") List<Long> servicosIds,
    @Param("diasSemana") List<ProjetoDto.DiaSemana> diasSemana
);
    
}
