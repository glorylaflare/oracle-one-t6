package br.com.alura.TabelaFipe.main;

import br.com.alura.TabelaFipe.model.Dados;
import br.com.alura.TabelaFipe.model.Modelos;
import br.com.alura.TabelaFipe.model.Veiculo;
import br.com.alura.TabelaFipe.service.ConsumoApiService;
import br.com.alura.TabelaFipe.service.ConverteDadosService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    Scanner scanner = new Scanner(System.in);
    private ConsumoApiService consumoApiService = new ConsumoApiService();
    private ConverteDadosService converteDadosService = new ConverteDadosService();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu() {
        var menu = """
                *** OPÇÕES ***
                1. Carro
                2. Moto
                3. Caminhão
                
                Digite uma das opções para consultar:""";

        System.out.println(menu);
        var opcao = scanner.nextLine();

        String endreco;
        if(opcao.toLowerCase().contains("carr")) {
            endreco = URL_BASE + "carros/marcas";
        } else if(opcao.toLowerCase().contains("mot")) {
            endreco = URL_BASE + "motos/marcas";
        } else {
            endreco = URL_BASE + "caminhoes/marcas";
        }

        var json = consumoApiService.obterDados(endreco);
        System.out.println(json);

        var marcas = converteDadosService.obterLista(json, Dados.class);
        marcas.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("Informe o código da marca para consultar modelos:");
        var codigoMarca = scanner.nextLine();
        endreco = endreco + "/" + codigoMarca + "/modelos";
        json = consumoApiService.obterDados(endreco);
        var modeloLista = converteDadosService.obterDados(json, Modelos.class);
        System.out.println("\nModelos dessa marca: ");
        modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

        System.out.println("\nDigite o modelo do carro para obter mais informações:");
        var modeloVeiculo = scanner.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(modeloVeiculo.toLowerCase()))
                .collect(Collectors.toList());

        System.out.println("\nModelos filtrados:");
        modelosFiltrados.forEach(System.out::println);

        System.out.println("Digite o código do modelo desejado:");
        var codigoModelo = scanner.nextLine();
        endreco = endreco + "/" + codigoModelo + "/anos";
        json = consumoApiService.obterDados(endreco);
        List<Dados> anosLista = converteDadosService.obterLista(json, Dados.class);
        List<Veiculo> veiculosLista = new ArrayList<>();

        for (int i = 0; i < anosLista.size(); i++) {
            var enderecoAnos = endreco + "/" + anosLista.get(i).codigo();
            json = consumoApiService.obterDados(enderecoAnos);
            Veiculo veiculo = converteDadosService.obterDados(json, Veiculo.class);
            veiculosLista.add(veiculo);
        }

        System.out.println("\nLista com todos os veículos filtrados: ");
        veiculosLista.forEach(System.out::println);
    }
}
