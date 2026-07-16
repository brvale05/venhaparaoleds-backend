package ifes.leds.desafio_backend.repository;

import ifes.leds.desafio_backend.domain.Concurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcursoRepository extends JpaRepository<Concurso, Long>
{
    Optional<Concurso> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    List<Concurso> findDistinctByVagasIn(List<String> vagasBuscadas);
}
