package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       String classificacao,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String sinopse,
                       Integer totalTemporadas,
                       String poster) {
}
