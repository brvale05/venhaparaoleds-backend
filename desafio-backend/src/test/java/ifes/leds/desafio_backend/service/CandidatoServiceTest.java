package ifes.leds.desafio_backend.service;

import ifes.leds.desafio_backend.exceptions.ObjetoNaoEncontradoException;
import ifes.leds.desafio_backend.repository.CandidatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CandidatoServiceTest
{
    @InjectMocks
    private CandidatoService candidatoService;

    @Mock
    private CandidatoRepository candidatoRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecaoAoBuscarCandidatoComCpfErrado()
    {
        String cpfInvalido = "123";

        assertThrows(ObjetoNaoEncontradoException.class, () -> {
            this.candidatoService.buscaCandidatoPorCpf(cpfInvalido);
        });
    }
}