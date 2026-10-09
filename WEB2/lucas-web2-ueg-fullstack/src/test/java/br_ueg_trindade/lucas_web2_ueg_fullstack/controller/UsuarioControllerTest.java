package br_ueg_trindade.lucas_web2_ueg_fullstack.controller;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Usuario;
import br_ueg_trindade.lucas_web2_ueg_fullstack.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    @DisplayName("Deve retornar lista de usuários via controller")
    void deveListarUsuarios() {
        Usuario usuario = new Usuario("Lucas", "lucas", "lucas@ueg.br", "123456");
        usuario.setId(1L);

        when(usuarioService.listarTodos()).thenReturn(List.of(usuario));

        List<Usuario> resultado = usuarioController.getAllUsuarios();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Lucas", resultado.get(0).getNome());
        verify(usuarioService, times(1)).listarTodos();
    }
}