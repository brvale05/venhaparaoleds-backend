package ifes.leds.desafio_backend.service;

import ifes.leds.desafio_backend.domain.Candidato;
import ifes.leds.desafio_backend.exceptions.ObjetoNaoEncontradoException;
import ifes.leds.desafio_backend.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidatoService
{
    private final CandidatoRepository candidatoRepository;

    public CandidatoService(CandidatoRepository candidatoRepository)
    {
        this.candidatoRepository = candidatoRepository;
    }

    public void salvaCandidato(Candidato candidato)
    {
        candidatoRepository.save(candidato);
    }

    public Candidato buscaCandidatoPorCpf(String cpf)
    {
        return candidatoRepository.findByCpf(cpf).orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum candidato com esse CPF foi encontrado"));
    }
}
