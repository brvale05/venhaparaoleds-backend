package ifes.leds.desafio_backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_candidato")
@Getter
@Setter
public class Candidato
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dtNasc;

    private String cpf;

    private List<String> profissoes = new ArrayList<>();

    public Candidato(String nome, LocalDate dtNasc, String cpf)
    {
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
    }

    public Candidato()
    {
    }
}
