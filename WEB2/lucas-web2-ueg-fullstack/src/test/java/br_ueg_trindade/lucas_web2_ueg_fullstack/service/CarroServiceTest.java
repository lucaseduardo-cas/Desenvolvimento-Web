package br_ueg_trindade.lucas_web2_ueg_fullstack.service;

import br_ueg_trindade.lucas_web2_ueg_fullstack.model.Carro;
import br_ueg_trindade.lucas_web2_ueg_fullstack.repository.CarroRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @Mock
    private CarroRepository carroRepository;

    @InjectMocks
    private CarroService carroService;

    @Test
    @DisplayName("Deve listar todos os carros com sucesso")
    void deveListarTodosOsCarros() {
        Carro carro = new Carro();
        carro.setId(1L);
        carro.setModelo("Civic");
        carro.setMarca("Honda");
        carro.setAno(2004);
        carro.setPlaca("ABC1D23");

        when(carroRepository.findAll()).thenReturn(List.of(carro));

        List<Carro> resultado = carroService.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Civic", resultado.get(0).getModelo());
        verify(carroRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve salvar um carro com sucesso")
    void deveCriarCarroComSucesso() {
        Carro carro = new Carro();
        carro.setModelo("Civic");
        carro.setPlaca("ABC1D23");

        when(carroRepository.save(any(Carro.class))).thenReturn(carro);

        Carro resultado = carroService.criar(carro);

        assertNotNull(resultado);
        assertEquals("Civic", resultado.getModelo());
        verify(carroRepository, times(1)).save(carro);
    }

    @Test
    @DisplayName("Deve disparar exceção ao buscar ID que não existe")
    void deveLancarExcecaoQuandoIdNaoExiste() {
        when(carroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> carroService.buscarPorId(99L));
        verify(carroRepository, times(1)).findById(99L);
    }
}