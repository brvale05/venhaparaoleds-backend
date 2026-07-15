package ifes.leds.desafio_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_concurso")
@Getter
@Setter
public class Concurso
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgao;

    private YearMonth dataEdital;

    private String codigo;

    private List<String> vagas = new ArrayList<>();

    public Concurso(String orgao, YearMonth dataEdital, String codigo)
    {
        this.orgao = orgao;
        this.dataEdital = dataEdital;
        this.codigo = codigo;
    }

    public Concurso()
    {
    }
}
