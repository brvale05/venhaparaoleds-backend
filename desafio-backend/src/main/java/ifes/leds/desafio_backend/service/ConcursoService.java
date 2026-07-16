package ifes.leds.desafio_backend.service;

import ifes.leds.desafio_backend.domain.Candidato;
import ifes.leds.desafio_backend.domain.Concurso;
import ifes.leds.desafio_backend.dto.response.ConcursoResponseDTO;
import ifes.leds.desafio_backend.exceptions.ObjetoNaoEncontradoException;
import ifes.leds.desafio_backend.service.CandidatoService;
import ifes.leds.desafio_backend.repository.ConcursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcursoService
{
    private final ConcursoRepository concursoRepository;

    public ConcursoService(ConcursoRepository concursoRepository)
    {
        this.concursoRepository = concursoRepository;
    }

    public void salvaConcurso(Concurso concurso)
    {
        if(!concursoRepository.existsByCodigo(concurso.getCodigo()))
        {
            concursoRepository.save(concurso);
        }
    }

    public Concurso buscaConcursoPorCodigo(String codigo)
    {
        return concursoRepository.findFirstByCodigo(codigo).orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum concurso com esse codigo foi encontrado"));
    }

    public List<ConcursoResponseDTO> buscaConcursosPorPerfilCandidato(List<String> vagasBuscadas)
    {
        return this.concursoRepository.findTop10DistinctByVagasIn(vagasBuscadas)
                .stream()
                .map(ConcursoService::toResponseDTO)
                .toList();
    }

    private static ConcursoResponseDTO toResponseDTO(Concurso concurso)
    {
        return new ConcursoResponseDTO(
                concurso.getOrgao(),
                concurso.getCodigo(),
                concurso.getEdital()
        );
    }
}
