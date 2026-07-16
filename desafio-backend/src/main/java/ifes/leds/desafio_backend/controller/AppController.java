package ifes.leds.desafio_backend.controller;

import ifes.leds.desafio_backend.domain.Candidato;
import ifes.leds.desafio_backend.domain.Concurso;
import ifes.leds.desafio_backend.dto.response.CandidatoResponseDTO;
import ifes.leds.desafio_backend.dto.response.ConcursoResponseDTO;
import ifes.leds.desafio_backend.service.CandidatoService;
import ifes.leds.desafio_backend.service.ConcursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController
{
    private final CandidatoService candidatoService;
    private final ConcursoService concursoService;

    public AppController(CandidatoService candidatoService, ConcursoService concursoService)
    {
        this.candidatoService = candidatoService;
        this.concursoService = concursoService;
    }

    @GetMapping("/{cpf}/concursos")
    public List<ConcursoResponseDTO> buscaConcursosPorPerfilCandidatoCpf(@PathVariable String cpf)
    {
        Candidato candidato = this.candidatoService.buscaCandidatoPorCpf(cpf);

        return this.concursoService.buscaConcursosPorPerfilCandidato(candidato.getProfissoes());
    }

    @GetMapping("/{codigo}/candidatos")
    public List<CandidatoResponseDTO> buscaCandidatosPorPerfilConcursoCodigo(@PathVariable String codigo)
    {
        Concurso concurso = this.concursoService.buscaConcursoPorCodigo(codigo);

        return this.candidatoService.buscaCandidatosPorPerfilConcurso(concurso.getVagas());
    }

    @GetMapping("teste/{nome}")
    public String teste(@PathVariable String nome)
    {
        return nome;
    }
}
