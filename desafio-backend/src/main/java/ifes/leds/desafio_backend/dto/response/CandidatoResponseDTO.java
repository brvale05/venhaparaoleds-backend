package ifes.leds.desafio_backend.dto.response;

import java.time.LocalDate;

public record CandidatoResponseDTO(

        String nome,

        LocalDate dtNasc,

        String cpf
)
{
}
