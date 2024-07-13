package com.alura.literalura.model.livro;

import com.alura.literalura.model.Idioma;
import com.alura.literalura.model.autor.Autor;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
        if(dadosLivro.autor() != null && !dadosLivro.autor().isEmpty()) {
            this.autor = new Autor(dadosLivro.autor().get(0));
        }
        this.idioma = Idioma.valueOf(dadosLivro.idioma().get(0));
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma=" + idioma +
                ", numeroDownloads=" + numeroDownloads +
                '}';
    }
}
