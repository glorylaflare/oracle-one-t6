package br.com.alura.screenmatch.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private int temporada;
    private String titulo;
    private int numeroEpisodio;
    private double avaliacao;
    private LocalDate data;

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodio) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numeroDoEpisodio();
        try {
            this.avaliacao = Double.parseDouble(dadosEpisodio.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
        try {
            this.data = LocalDate.parse(dadosEpisodio.data());
        } catch (DateTimeParseException d) {
            this.data = null;
        }
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Episodio{" +
                "temporada=" + temporada +
                ", titulo='" + titulo + '\'' +
                ", numeroEpisodio=" + numeroEpisodio +
                ", avaliacao=" + avaliacao +
                ", data=" + data +
                '}';
    }
}
