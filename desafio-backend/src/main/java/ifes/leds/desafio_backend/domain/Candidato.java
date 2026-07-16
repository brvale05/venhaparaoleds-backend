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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> profissoes = new ArrayList<>();

    public Candidato(String nome, LocalDate dtNasc, String cpf, List<String>profissoes)
    {
        this.nome = nome;
        this.dtNasc = dtNasc;
        this.cpf = cpf;
        this.profissoes = profissoes;
    }

    public Candidato()
    {
    }

    @Override
    public String toString()
    {
        return "Candidato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dtNasc=" + dtNasc +
                ", cpf='" + cpf + '\'' +
                ", profissoes=" + profissoes +
                '}';
    }
}
