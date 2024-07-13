package com.alura.literalura.model.livro;

import com.alura.literalura.model.autor.DadosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
         @JsonAlias("title") String titulo,
         @JsonAlias("authors") List<DadosAutor> autor,
         @JsonAlias("languages") List<String> idioma,
         @JsonAlias("download_count") Integer numeroDownloads) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- LIVRO -----\n");
        sb.append("Título: ").append(titulo).append("\n");

        if (autor != null && !autor.isEmpty()) {
            String nomesAutores = autor.stream()
                    .map(DadosAutor::nome)
                    .collect(Collectors.joining(", "));
            sb.append("Autor: ").append(nomesAutores).append("\n");
        } else {
            sb.append("Autor: N/A\n");
        }

        if (idioma != null && !idioma.isEmpty()) {
            sb.append("Idioma: ").append(idioma.get(0)).append("\n");  // Pega o primeiro idioma
        } else {
            sb.append("Idioma: N/A\n");
        }

        sb.append("Número de downloads: ").append(numeroDownloads != null ? numeroDownloads : 0).append("\n");
        sb.append("-----------------\n");
        return sb.toString();
    }
}
