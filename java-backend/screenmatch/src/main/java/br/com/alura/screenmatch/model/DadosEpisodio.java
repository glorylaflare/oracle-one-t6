package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") Integer numeroDoEpisodio,
        @JsonAlias("Runtime") String duracaoDoEpisodio,
        @JsonAlias("imdbRating") String avaliacao,
        @JsonAlias("Released") String data) {
}
