package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.models.DadosSerie;
import br.com.alura.screenmatch.models.DadosTemporada;
import br.com.alura.screenmatch.models.Episodio;
import br.com.alura.screenmatch.models.Serie;
import br.com.alura.screenmatch.repositories.SerieRepository;
import br.com.alura.screenmatch.services.ConsumoApiService;
import br.com.alura.screenmatch.services.ConverteDadosService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String API_KEY = System.getenv("OMDB_API_KEY");
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApiService consumoApiService = new ConsumoApiService();
    private final ConverteDadosService converteDadosService = new ConverteDadosService();
    private List<DadosSerie> dadosSeriesLista = new ArrayList<>();

    private SerieRepository serieRepository;
    private List<Serie> series = new ArrayList<>();

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                                    
                    0 - Sair""";

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarSeriesBuscadas() {
        series = serieRepository.findAll();
        series.stream().sorted(Comparator.comparing(Serie::getGenero)).forEach(System.out::println);
    }

    private void buscarSerieWeb() {
        DadosSerie dadosSerie = getDadosSerie();
        Serie serie = new Serie(dadosSerie);
        serieRepository.save(serie);
        System.out.println(dadosSerie);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome de uma série para efetuar busca: ");
        var nomeSerie = scanner.nextLine();
        var json = consumoApiService.obterDados("https://www.omdbapi.com/?t="+ nomeSerie.replace(" ", "+") +"&apikey="+ API_KEY);
        DadosSerie dadosSerie = converteDadosService.obterDados(json, DadosSerie.class);
        return dadosSerie;
    }

    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Escolha uma série: ");
        var nomeSerie = scanner.nextLine();

        Optional<Serie> serie = series
                .stream().filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();
        if(serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApiService.obterDados("https://www.omdbapi.com/?t="+ serieEncontrada.getTitulo().replace(" ", "+") +"&season="+ i +"&apikey="+ API_KEY);
                DadosTemporada dadosTemporada = converteDadosService.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);

            serieRepository.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada!");
        }
    }
}
