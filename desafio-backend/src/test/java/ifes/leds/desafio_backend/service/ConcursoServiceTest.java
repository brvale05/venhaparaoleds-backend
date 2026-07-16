package ifes.leds.desafio_backend.service;

import ifes.leds.desafio_backend.exceptions.ObjetoNaoEncontradoException;
import ifes.leds.desafio_backend.repository.ConcursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ConcursoServiceTest
{
    @InjectMocks
    private ConcursoService concursoService;

    @Mock
    private ConcursoRepository concursoRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecaoAoBuscarConcursoPorCodigoErrado()
    {
        String codigoInvalido = "123";

        assertThrows(ObjetoNaoEncontradoException.class, () -> {
            this.concursoService.buscaConcursoPorCodigo(codigoInvalido);
        });
    }
}