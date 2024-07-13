package com.alura.literalura.model.livro;

import com.alura.literalura.model.Idioma;
import com.alura.literalura.model.autor.Autor;
import com.alura.literalura.model.autor.DadosAutor;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Table(name = "livros")
@Entity(name = "Livro")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDownloads;

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.idioma = Idioma.valueOf(dadosLivro.idioma().get(0));
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- LIVRO -----\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor: ").append(autor.getNome()).append("\n");
        sb.append("Idioma: ").append(idioma.name()).append("\n");
        sb.append("Número de downloads: ").append(numeroDownloads).append("\n");
        sb.append("-----------------\n");
        return sb.toString();
    }
}
