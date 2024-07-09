package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApiService;
import br.com.alura.screenmatch.service.ConverteDadosService;

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
    private Optional<Serie> serieBusca;

    public Main(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Buscar série por título
                    4 - Buscar séries por ator
                    5 - Buscar séries por categoria
                    6 - Buscar episódios de uma série por trecho
                    7 - Listar séries buscadas
                    8 - Top 5 séries
                    9 - Top 5 episódios de uma série
                    10 - Filtrar séries
                    11 - Buscar episódios a partir de uma data
                                    
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
                    buscarSeriePorTitulo();
                    break;
                case 4:
                    buscarSeriesPorAtor();
                    break;
                case 5:
                    buscarSeriesPorCategoria();
                    break;
                case 6:
                    buscarEpisodioPorTrecho();
                    break;
                case 7:
                    listarSeriesBuscadas();
                    break;
                case 8:
                    buscarTop5Series();
                    break;                
                case 9:
                    topEpisodiosPorSerie();
                    break;
                case 10:
                    filtrarSeriesPorTemporadaEAvaliacao();
                    break;
                case 11:
                    buscarEpisodiosDepoisDeUmaData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarEpisodiosDepoisDeUmaData() {
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            System.out.println("Digite o ano limite de lançamento: ");
            var anoLancamento = scanner.nextInt();
            scanner.nextLine();

            List<Episodio> episodiosAno = serieRepository.episodiosPorSerieEAno(serie, anoLancamento);
            episodiosAno.forEach(System.out::println);
        }
    }

    private void topEpisodiosPorSerie() {
        buscarSeriePorTitulo();
        if(serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = serieRepository.topEpisodiosPorSerie(serie);

            topEpisodios.forEach(e ->
                    System.out.printf("Série: %s Temporada %s - Episódio %s - %s - Avaliação %s\n",
                            e.getSerie().getTitulo(), e.getTemporada(),
                            e.getNumeroEpisodio(), e.getTitulo(), e.getAvaliacao()));
        }
    }

    private void buscarEpisodioPorTrecho() {
        System.out.println("Digite o nome de um episódio para buscar no banco de dados: ");
        var trechoEpisodio = scanner.nextLine();

        List<Episodio> episodiosEncontrados = serieRepository.episodiosPorTrecho(trechoEpisodio);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Série: %s Temporada %s - Episódio %s - %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));
    }

    private void filtrarSeriesPorTemporadaEAvaliacao() {
        System.out.println("Filtrar séries de até quantas temporadas? ");
        var totalTemporadas = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Com avaliação a partir de que valor? ");
        var avaliacao = scanner.nextDouble();
        scanner.nextLine();
        List<Serie> filtroSeries = serieRepository.seriesPorTemporadaEAvaliacao(totalTemporadas, avaliacao);
        System.out.println("Séries filtradas por temporadas e avaliação:");
        filtroSeries.forEach(s ->
                System.out.println("[" + s.getTitulo() + ", Avaliação: " + s.getAvaliacao() + "]"));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Escolha uma categoria/gênero para buscar uma série: ");
        var nomeCategoria = scanner.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeCategoria);
        List<Serie> seriesPorCategoria = serieRepository.findByGenero(categoria);
        System.out.println("Séries da categoria " + nomeCategoria);
        seriesPorCategoria.forEach(System.out::println);
    }

    private void buscarTop5Series() {
        List<Serie> serieTop = serieRepository.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s ->
                System.out.println("[" + s.getTitulo() + ", Avaliação: " + s.getAvaliacao() + "]"));
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Digite o nome de uma ator para buscar no banco de dados: ");
        var nomeAtor = scanner.nextLine();
        System.out.println("Avaliações a partir de qual valor? ");
        var avaliacao = scanner.nextDouble();

        List<Serie> seriesEncontradas = serieRepository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);
        System.out.println("Séries em que "+ nomeAtor +" trabalhou:");
        seriesEncontradas.forEach(s ->
                System.out.println("[" + s.getTitulo() + ", Avaliação: " + s.getAvaliacao() + "]"));
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma série: ");
        var nomeSerie = scanner.nextLine();
        serieBusca = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if(serieBusca.isPresent()) {
            System.out.println("Dados da série: " + serieBusca.get());
        } else {
            System.out.println("Série não encontrada!");
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

        Optional<Serie> serie = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

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
