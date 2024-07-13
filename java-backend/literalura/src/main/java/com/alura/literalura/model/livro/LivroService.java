package com.alura.literalura.model.livro;

import com.alura.literalura.model.DadosResults;
import com.alura.literalura.model.autor.Autor;
import com.alura.literalura.model.autor.AutorRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Service
public class LivroService {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ConverteDados converteDados = new ConverteDados();

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarLivroPorTitulo() {
        DadosLivro dadosLivro = getDadosLivro();
        System.out.println(dadosLivro);
        assert dadosLivro != null;
        Livro livro = new Livro(dadosLivro);
        livroRepository.save(livro);
        System.out.println("Livro adicionado com sucesso ao banco de dados!\n");
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

    public void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        livros.stream().sorted(Comparator.comparing(Livro::getId)).forEach(System.out::println);
    }

    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.stream().sorted(Comparator.comparing(Autor::getId)).forEach(System.out::println);
    }
}
