package ifes.leds.desafio_backend.repository;

import ifes.leds.desafio_backend.domain.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long>
{
    Optional<Candidato> findByCpf(String cpf);

    List<Candidato> findDistinctByProfissoesIn(List<String> profissoesBuscadas);
}
