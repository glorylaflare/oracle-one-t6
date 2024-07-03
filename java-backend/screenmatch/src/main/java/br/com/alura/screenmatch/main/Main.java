package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.models.DadosEpisodio;
import br.com.alura.screenmatch.models.DadosSerie;
import br.com.alura.screenmatch.models.DadosTemporada;
import br.com.alura.screenmatch.models.Episodio;
import br.com.alura.screenmatch.services.ConsumoApiService;
import br.com.alura.screenmatch.services.ConverteDadosService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApiService consumoApiService = new ConsumoApiService();
    private final ConverteDadosService converteDadosService = new ConverteDadosService();

    public void exibeMenu() {
        System.out.println("Digite o nome de uma série para efetuar busca: ");
        var nomeSerie = scanner.nextLine();
        var json = consumoApiService.obterDados("https://www.omdbapi.com/?t="+ nomeSerie.replace(" ", "+") +"&apikey=633c6ea0");
        DadosSerie dadosSerie = converteDadosService.obterDados(json, DadosSerie.class);
        //System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoApiService.obterDados("https://www.omdbapi.com/?t="+ nomeSerie.replace(" ", "+") +"&season="+ i +"&apikey=633c6ea0");
            DadosTemporada dadosTemporada = converteDadosService.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        /*
        temporadas.forEach(System.out::println);

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("## Top 5 episódios");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e-> System.out.println("Primeiro filtro (N/A) -> " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e-> System.out.println("Ordenação -> " + e))
                .limit(5)
                .peek(e-> System.out.println("Limite -> " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e-> System.out.println("Transformando o título em maiúsculo -> " + e))
                .forEach(System.out::println);

        */
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());
        /*
        episodios.forEach(System.out::println);

        System.out.println("Digite uma palavra para buscar um episódio:");
        var trechoTitulo = scanner.nextLine();
        Optional<Episodio> buscaEpisodio = episodios.stream()
                .filter(e -> e.getTitulo().toLowerCase().contains(trechoTitulo.toLowerCase()))
                .findFirst();

        if(buscaEpisodio.isPresent()) {
            System.out.println("Episódio encontrado!");
            System.out.println("Temporada: " + buscaEpisodio.get().getTemporada());
        } else {
            System.out.println("Episódio não encontrado!");
        }

        System.out.println("A partir de qual ano você deseja ver os episódios?");
        var ano = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream()
                .filter(e -> e.getData() != null && e.getData().isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() + ", " +
                        "Episódio: " + e.getTitulo() + ", " +
                        "Data de lançamento: " + e.getData().format(dateTimeFormatter)
                ));

         */

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));
        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics doubleSummaryStatistics = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println("Quantidade: " + doubleSummaryStatistics.getCount());
        System.out.println("Melhor episódio: " + doubleSummaryStatistics.getMax());
        System.out.println("Pior episódio: " + doubleSummaryStatistics.getMin());
        System.out.printf("Média: %.2f\n", doubleSummaryStatistics.getAverage());
    }
}
