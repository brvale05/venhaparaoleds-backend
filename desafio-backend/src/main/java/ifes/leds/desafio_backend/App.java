package ifes.leds.desafio_backend;

import ifes.leds.desafio_backend.domain.Candidato;
import ifes.leds.desafio_backend.repository.CandidatoRepository;
import ifes.leds.desafio_backend.service.CandidatoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner
{
    private CandidatoService candidatoService;

    public App(CandidatoService candidatoService)
    {
        this.candidatoService = candidatoService;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        leCandidatos();

    }

    private void leCandidatos()
    {
        String path = "../candidatos.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(path)))
        {
            String line = br.readLine();

            while (line != null)
            {
                line = br.readLine();

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

                System.out.println(candidatoService.buscaCandidatoPorCpf(cpf).toString());

                break;
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
