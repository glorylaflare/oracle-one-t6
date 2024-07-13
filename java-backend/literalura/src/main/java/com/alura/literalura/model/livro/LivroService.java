package com.alura.literalura.model.livro;

import com.alura.literalura.model.DadosResults;
import com.alura.literalura.model.Idioma;
import com.alura.literalura.model.autor.Autor;
import com.alura.literalura.model.autor.AutorRepository;
import com.alura.literalura.model.autor.DadosAutor;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    public void buscarLivroPorTitulo() {
        DadosLivro dadosLivro = getDadosLivro();

        if (dadosLivro == null || dadosLivro.titulo() == null || dadosLivro.autor() == null || dadosLivro.autor().isEmpty()) {
            System.out.println("Livro não encontrado na API. Verifique o título ou tente novamente.");
            return;
        }

        System.out.println(dadosLivro);
        Livro livro = new Livro(dadosLivro);

        var nomeAutor = dadosLivro.autor().get(0);
        Optional<Autor> autorExistente = autorRepository.findByNome(nomeAutor.nome());

        if(autorExistente.isPresent()) {
            livro.setAutor(autorExistente.get());
        } else {
            Autor novoAutor = new Autor(nomeAutor);
            autorRepository.save(novoAutor);
            livro.setAutor(novoAutor);
        }

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

    @Transactional
    public void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getId))
                .forEach(System.out::println);
    }

    @Transactional
    public void listarAutoresVivosPorAno() {
        System.out.println("Insira o ano que deseja pesquisar:");
        var ano = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");

        List<Autor> autores = autorRepository.findByAnoNascimentoLessThanAndAnoFalecimentoGreaterThan(ano, ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + ano + ".");
        } else {
            autores.stream()
                    .sorted(Comparator.comparing(Autor::getId))
                    .forEach(System.out::println);
        }
    }

    public void listarLivrosPorIdioma() {
        System.out.println("""
                Insira o idioma para realizar a busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var idioma = scanner.nextLine().trim();

        if (idioma.isEmpty()) {
            System.out.println("Por favor, insira um idioma válido.");
            return;
        }

        Idioma idiomaEnum;
        try {
            idiomaEnum = Idioma.valueOf(idioma.toLowerCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma inválido. Por favor, insira um idioma correto.");
            return;
        }

        List<Livro> livros = livroRepository.findByIdioma(idiomaEnum);

        if (livros.isEmpty()) {
            System.out.println("Não existem livros nesse idioma no banco de dados.");
        } else {
            livros.stream()
                    .sorted(Comparator.comparing(Livro::getId))
                    .forEach(System.out::println);
        }
    }
}