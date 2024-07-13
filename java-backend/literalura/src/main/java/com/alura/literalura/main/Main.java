package com.alura.literalura.main;

import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);

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
        System.out.println("Opção: 1");
    }
}
