package ifes.leds.desafio_backend;

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
    public App()
    {
    }

    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        String candidatosPath = "../candidatos.txt";
        String concursoPath = "../concursos.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(candidatosPath)))
        {
            String line = br.readLine();

            while (line != null)
            {
                line = br.readLine();

                String[] lineSplit = line.split(",", 4);

                String nome = lineSplit[0];
                String dtNasc = lineSplit[1];
                String cpf = lineSplit[2];
                String profissoes = lineSplit[3];

                String profissoesAtualizadas = profissoes.replaceAll("[\"\\[\\]]", "");

                List<String> profissoesList = Arrays.asList(profissoesAtualizadas.split(","));

                List<String> profissoesListAtualizadas = profissoesList.stream().map(String::trim).toList();

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                LocalDate dataNasc = LocalDate.parse(dtNasc, formato);

                System.out.println(dataNasc);

                System.out.println(nome + " " + dtNasc + " " + cpf + " " + profissoesListAtualizadas);

                break;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
