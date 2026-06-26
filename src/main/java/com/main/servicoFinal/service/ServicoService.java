
package com.main.servicoFinal.service;

import com.main.servicoFinal.model.ServicoDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.model.UsuarioServicoDto;
import com.main.servicoFinal.repository.ServiceRepository;
import com.main.servicoFinal.repository.UserRepository;
import com.main.servicoFinal.repository.UsuarioServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
