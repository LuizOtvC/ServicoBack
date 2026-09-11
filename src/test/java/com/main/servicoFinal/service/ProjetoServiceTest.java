package com.main.servicoFinal.service;

import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.ProjetoUserDto;
import java.util.List;

import com.main.servicoFinal.model.User;
import com.main.servicoFinal.repository.ProjetoRepository;
import com.main.servicoFinal.repository.ProjetoServicoRepository;
import com.main.servicoFinal.repository.ServiceRepository;
import com.main.servicoFinal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;
    @Mock
    private UserRepository user;
    @Mock
    private MensagemService mensagemService;
    @Mock
    private ServiceRepository serviceRepository;
    @Mock
    private ProjetoServicoRepository projetoServicoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    private Long usuarioId;
    private ProjetoUserDto dados;

    @BeforeEach
    void setUp() {
        usuarioId = 1L;
        dados = new ProjetoUserDto();
        dados.setTitulo("Reforma do banheiro");
        dados.setServicosId(List.of());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioJaTemTresProjetosAtivos() {
        // Arrange: simula que o usuário JÁ tem 3 projetos ativos
        when(projetoRepository.countByUsuarioIdIdAndStatusIn(eq(usuarioId), anyList()))
                .thenReturn(3L);

        // Act + Assert: espera que lance a exceção com a mensagem certa
        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            projetoService.criarProjeto(usuarioId, dados);
        });
        assertEquals("Você já possui 3 projetos em aberto ou em andamento.", ex.getMessage());

        // garante que nem chegou a salvar nada, já que foi barrado antes
        verify(projetoRepository, never()).save(any());
    }

    @Test
    void devePermitirQuandoUsuarioTemMenosDeTresProjetosAtivos() {
        // Arrange: simula que o usuário tem só 2 (abaixo do limite)
        when(projetoRepository.countByUsuarioIdIdAndStatusIn(eq(usuarioId), anyList()))
                .thenReturn(2L);
        when(projetoRepository.existsByUsuarioIdIdAndTituloIgnoreCase(usuarioId, dados.getTitulo()))
                .thenReturn(false);
        when(user.getReferenceById(usuarioId))
                .thenReturn(new User());

        // Act: não deve lançar nenhuma exceção
        assertDoesNotThrow(() -> {
            projetoService.criarProjeto(usuarioId, dados);
        });

        // Assert: confirma que realmente tentou salvar
        verify(projetoRepository, times(1)).save(any(ProjetoDto.class));
    }
}