package com.alura.literalura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDownloads;

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        if(dadosLivro.autor() != null && !dadosLivro.autor().isEmpty()) {
            this.autor = new Autor(dadosLivro.autor().get(0));
        }
        this.idioma = Idioma.valueOf(dadosLivro.idioma().get(0));
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }
}
