
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.ServicoAtualizar;
import com.main.servicoFinal.model.ServicoDto;
import com.main.servicoFinal.model.ServicoListar;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.model.UsuarioServicoId;
import com.main.servicoFinal.repository.ServiceRepository;
import com.main.servicoFinal.repository.UserRepository;
import com.main.servicoFinal.repository.UsuarioServicoRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServicoService {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private UsuarioServicoRepository servcicouser;
     
    public void adicionarHabilidade(Long usuarioId, Long servicoId, UsuarioServicoDto.Nivel nivel) {
    
    User user = repository.findById(usuarioId)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    ServicoDto servico = serviceRepository.findById(servicoId)
        .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

    UsuarioServicoDto usuarioServico = new UsuarioServicoDto();
    usuarioServico.setUsuario(user);
    usuarioServico.setServico(servico);
    usuarioServico.setNivel(nivel);

    servcicouser.save(usuarioServico);
}
    public List<ServicoDto> listarTodosServicos(){
        return serviceRepository.findAllByOrderByIdAsc();
    }
    
    public List<ServicoListar> listarHabilidades(Long usuarioId) {
    List<UsuarioServicoDto> lista = servcicouser.findByUsuarioId(usuarioId);
    List<ServicoListar> resultado = new ArrayList<>();

    for (UsuarioServicoDto us : lista) {
        resultado.add(new ServicoListar(us.getServico().getId(), us.getServico().getNome(),us.getNivel().name()));
    }

    return resultado;
}
    @Transactional
    public void atualizarHabilidade(Long usuarioId, ServicoAtualizar dados) {
    UsuarioServicoId idAntigo = new UsuarioServicoId(usuarioId, dados.getIdAntigo());
    Optional<UsuarioServicoDto> antigo = servcicouser.findById(idAntigo);
if (antigo.isEmpty()) {
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Habilidade não encontrada");
}
servcicouser.delete(antigo.get());

    UsuarioServicoId idNovo = new UsuarioServicoId(usuarioId, dados.getIdNovo());
    UsuarioServicoDto novo = new UsuarioServicoDto();
    novo.setId(idNovo);
    novo.setUsuario(repository.getReferenceById(usuarioId));
    novo.setServico(serviceRepository.getReferenceById(dados.getIdNovo()));
    novo.setNivel(dados.getNivel());
    servcicouser.save(novo);
}
    
   public void deletarHabilidade(Long usuarioId, Long servicoId) {
    servcicouser.deleteById(new UsuarioServicoId(usuarioId, servicoId));  
}
}
