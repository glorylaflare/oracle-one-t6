package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.translate.ConsultaMyMemoryService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
@Getter @Setter @NoArgsConstructor
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String classificacao;
    private double avaliacao;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String atores;
    private String sinopse;
    private int totalTemporadas;
    private String poster;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Episodio> episodios = new ArrayList<>();

    public Serie(DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.classificacao = dadosSerie.classificacao();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.sinopse = ConsultaMyMemoryService.obterTraducao(dadosSerie.sinopse()).trim();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.poster = dadosSerie.poster();
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "titulo='" + titulo + '\'' +
                ", classificacao='" + classificacao + '\'' +
                ", avaliacao=" + avaliacao +
                ", genero=" + genero +
                ", atores='" + atores + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", poster='" + poster + '\'' +
                ", episodios=" + episodios +
                '}';
    }
}
