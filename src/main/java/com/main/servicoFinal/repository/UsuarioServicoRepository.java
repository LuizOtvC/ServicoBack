
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.UsuarioServicoDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioServicoRepository extends JpaRepository<UsuarioServicoDto, Long>{
   
List<UsuarioServicoDto> findByUsuarioId(Long usuarioId);
}
