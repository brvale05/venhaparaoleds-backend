package ifes.leds.desafio_backend.repository;

import ifes.leds.desafio_backend.domain.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long>
{
    Optional<Candidato> findFirstByCpf(String cpf);

    boolean existsByCpf(String cpf);

    List<Candidato> findTop10DistinctByProfissoesIn(List<String> profissoesBuscadas);
}
