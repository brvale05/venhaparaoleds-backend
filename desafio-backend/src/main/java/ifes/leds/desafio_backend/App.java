package ifes.leds.desafio_backend;

import ifes.leds.desafio_backend.domain.Candidato;
import ifes.leds.desafio_backend.domain.Concurso;
import ifes.leds.desafio_backend.repository.CandidatoRepository;
import ifes.leds.desafio_backend.service.CandidatoService;
import ifes.leds.desafio_backend.service.ConcursoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App implements CommandLineRunner
{
    private CandidatoService candidatoService;
    private ConcursoService concursoService;

    public App(CandidatoService candidatoService, ConcursoService concursoService)
    {
        this.candidatoService = candidatoService;
        this.concursoService = concursoService;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        leCandidatos();
        leConcursos();
    }

    private void leConcursos()
    {
        String path = "../concursos.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            //Joga fora primeira linha
            String line = br.readLine();

            line = br.readLine();

            while (line != null)
            {
                String[] lineSplit = line.split(",", 4);

                String orgao = lineSplit[0];
                String edital = lineSplit[1];
                String codigo = lineSplit[2];
                String vagasBrutas = lineSplit[3];

                String vagasFormatadas = vagasBrutas.replaceAll("[\"\\[\\]]", "");

                List<String> vagasList = Arrays.asList(vagasFormatadas.split(","));

                List<String> vagasListFormatadas = vagasList.stream().map(String::trim).toList();

                criaConcursoNoBanco(orgao, edital, codigo, vagasListFormatadas);

                line = br.readLine();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void criaConcursoNoBanco(String orgao, String edital, String codigo, List<String> vagas)
    {
        Concurso concurso = new Concurso(orgao, edital, codigo, vagas);

        this.concursoService.salvaConcurso(concurso);
    }

    private void leCandidatos()
    {
        String path = "../candidatos.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            String line = br.readLine();

            line = br.readLine();

            while (line != null)
            {
                String[] lineSplit = line.split(",", 4);

                String nome = lineSplit[0];
                String dtNascBruta = lineSplit[1];
                String cpf = lineSplit[2].replaceAll("[^0-9]", "");
                String profissoesBrutas = lineSplit[3];

                String profissoesFormatadas = profissoesBrutas.replaceAll("[\"\\[\\]]", "");

                List<String> profissoesList = Arrays.asList(profissoesFormatadas.split(","));

                List<String> profissoesListFormatadas = profissoesList.stream().map(String::trim).toList();

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate dtNascFormatada = LocalDate.parse(dtNascBruta, formato);

                criaCandidatoNoBanco(nome, dtNascFormatada, cpf, profissoesListFormatadas);

                line = br.readLine();

            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void criaCandidatoNoBanco(String nome, LocalDate dtNasc, String cpf, List<String> profissoes)
    {
        Candidato candidato = new Candidato(nome, dtNasc, cpf, profissoes);

        this.candidatoService.salvaCandidato(candidato);
    }
}
