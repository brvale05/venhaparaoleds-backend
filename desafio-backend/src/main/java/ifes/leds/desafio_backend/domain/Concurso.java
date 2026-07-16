package ifes.leds.desafio_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private String edital;

    private String codigo;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> vagas;

    public Concurso(String orgao, String edital, String codigo, List<String> vagas)
    {
        this.orgao = orgao;
        this.edital = edital;
        this.codigo = codigo;
        this.vagas = vagas;
    }

    public Concurso()
    {
    }

    @Override
    public String toString()
    {
        return "Concurso{" +
                "id=" + id +
                ", orgao='" + orgao + '\'' +
                ", edital='" + edital + '\'' +
                ", codigo='" + codigo + '\'' +
                ", vagas=" + vagas +
                '}';
    }
}
