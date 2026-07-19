package ifes.leds.desafio_backend.service;

import ifes.leds.desafio_backend.domain.Candidato;
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

    public long count()
    {
        return this.candidatoRepository.count();
    }

    public void salvaCandidato(Candidato candidato)
    {
        if (this.candidatoRepository.findByCpf(candidato.getCpf()).isEmpty())
        {
            this.candidatoRepository.save(candidato);
        }

    }

    public Candidato buscaCandidatoPorCpf(String cpf)
    {
        return candidatoRepository.findByCpf(cpf).orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum candidato com esse CPF foi encontrado"));
    }

    // Busca número 2 do README:
    // Listar o nome, data de nascimento e o CPF dos candidatos
    // que se encaixam no perfil do concurso tomando com base o Código do Concurso do concurso público;
    public List<CandidatoResponseDTO> buscaCandidatosPorPerfilConcurso(List<String> profissoesBuscadas)
    {
        return candidatoRepository.findDistinctByProfissoesIn(profissoesBuscadas)
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
