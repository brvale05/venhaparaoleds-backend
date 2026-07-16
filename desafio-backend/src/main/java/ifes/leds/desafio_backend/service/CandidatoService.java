package ifes.leds.desafio_backend.service;

import ifes.leds.desafio_backend.domain.Candidato;
import ifes.leds.desafio_backend.domain.Concurso;
import ifes.leds.desafio_backend.dto.response.CandidatoResponseDTO;
import ifes.leds.desafio_backend.exceptions.ObjetoNaoEncontradoException;
import ifes.leds.desafio_backend.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(!candidatoRepository.existsByCpf(candidato.getCpf()))
        {
            candidatoRepository.save(candidato);
        }
    }

    public Candidato buscaCandidatoPorCpf(String cpf)
    {
        return candidatoRepository.findFirstByCpf(cpf).orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum candidato com esse CPF foi encontrado"));
    }

    public List<CandidatoResponseDTO> buscaCandidatosPorPerfilConcurso(List<String> profissoesBuscadas)
    {
        return candidatoRepository.findTop10DistinctByProfissoesIn(profissoesBuscadas)
                .stream()
                .map(CandidatoService::toResponse)
                .toList();
    }

    private static CandidatoResponseDTO toResponse(Candidato candidato)
    {
        return new CandidatoResponseDTO(
                candidato.getNome(),
                candidato.getDtNasc(),
                candidato.getCpf()
        );
    }
}
