package com.alura.literalura.main;

import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.DadosResults;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados converteDados = new ConverteDados();

    public void exibirMenu() {
        var opcao = -1;

        while(opcao != 0) {
            var menu = """
                        ------------------
                        Escolha o número de sua opção:
                        
                        1 - Buscar livro pelo título
                        2 - Listar livros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos em um determinado ano
                        5 - Listar livros em um determinado idioma
                                        
                        0 - Sair
                        ------------------""";

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Opção: 5");
    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Opção: 4");
    }

    private void listarAutoresRegistrados() {
        System.out.println("Opção: 3");
    }

    private void listarLivrosRegistrados() {
        System.out.println("Opção: 2");
    }

    private void buscarLivroPorTitulo() {
        DadosLivro dadosLivro = getDadosLivro();
        System.out.println(dadosLivro);
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Digite o título do livro que você deseja buscar:");
        var tituloDoLivro = scanner.nextLine();
        var json = consumoApi.enviarRequisicao("https://gutendex.com/books/?search=" + tituloDoLivro.replace(" ", "+"));

        DadosResults dadosResults = converteDados.obterDados(json, DadosResults.class);
        if(dadosResults != null && dadosResults.livros() != null && !dadosResults.livros().isEmpty()) {
            return dadosResults.livros().get(0);
        } else {
            System.err.println("Nenhum livro encontrado.");
            return null;
        }
    }
}
