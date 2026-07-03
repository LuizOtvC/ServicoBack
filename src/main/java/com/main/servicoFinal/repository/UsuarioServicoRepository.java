
package com.main.servicoFinal.repository;

import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.model.UsuarioServicoId;
import java.util.List;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioServicoRepository extends JpaRepository<UsuarioServicoDto, UsuarioServicoId>{
   
List<UsuarioServicoDto> findByUsuarioId(Long usuarioId);

    public void deleteById(UsuarioServicoId idAntigo);


}
