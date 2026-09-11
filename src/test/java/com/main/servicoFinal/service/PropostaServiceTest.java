package com.main.servicoFinal.service;

import com.main.servicoFinal.model.ProjetoDto;
import com.main.servicoFinal.model.ProjetoUserDto;
import com.main.servicoFinal.model.PropostaDto;
import com.main.servicoFinal.model.User;
import com.main.servicoFinal.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropostaServiceTest {
    @Mock
    private PropostaRepository propostaRepository;
    @Mock
    private UserRepository user;
    @Mock
    private MensagemService mensagemService;
    @Mock
    private MatchService matchService;
    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private PropostaService propostaService;

    private Long usuarioId;
    private Long projetoId;
    private PropostaDto dados;

    @BeforeEach
    void setUp() {
        usuarioId = 1L;
        projetoId = 10L;
        dados = new PropostaDto();
        dados.setValorProposto(500.0);
        dados.setDescricao("Proposta de teste");
    }

    @Test
    void naoDevePermitirEnviarPropostaQuandoJaExisteUmaDoUsuarioNoProjeto() {
        when(propostaRepository.existsByUsuarioIdAndProjetoId(usuarioId, projetoId))
                .thenReturn(true);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            propostaService.criarProposta(usuarioId, projetoId, dados.getValorProposto(), dados.getDescricao());
        });
        assertEquals("Você já enviou uma proposta para este projeto.", ex.getMessage());

        verify(propostaRepository, never()).save(any());
    }

    @Test
    void devePermitirEnviarPropostaQuandoUsuarioAindaNaoTemPropostaNoProjeto(){
        when(propostaRepository.existsByUsuarioIdAndProjetoId(usuarioId, projetoId))
                .thenReturn(true);
        when(user.getReferenceById(usuarioId))
                .thenReturn(new User());
        when(projetoRepository.getReferenceById(projetoId))
                .thenReturn(new ProjetoDto());

        assertDoesNotThrow(() -> {
            propostaService.criarProposta(usuarioId, projetoId, dados.getValorProposto(), dados.getDescricao());
        });

        verify(propostaRepository, times(1)).save(any(PropostaDto.class));
    }
}