package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDTO;
import br.com.alura.screenmatch.dto.SerieDTO;
import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public List<SerieDTO> obterTodasAsSeries() {
        return converteDados(serieRepository.findAll());
    }

    public List<SerieDTO> obterTop5Series() {
        return converteDados(serieRepository.findTop5ByOrderByAvaliacaoDesc());
    }

    public List<SerieDTO> obterLancamentos() {
        return converteDados(serieRepository.findTop5ByOrderByEpisodiosDataDesc());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if(serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getClassificacao(), s.getAvaliacao(), s.getGenero(),
                    s.getAtores(), s.getSinopse(), s.getTotalTemporadas(), s.getPoster());
        }
        return null;
    }

    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = serieRepository.findById(id);
        if(serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obterTemporada(Long id, Integer num) {
        return serieRepository.obterEpisodiosPorTemporada(id, num)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obterSeriesPorCategoria(String categoria) {
        Categoria c = Categoria.fromPortugues(categoria);
        return converteDados(serieRepository.findByGenero(c));
    }

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream().map(s -> new SerieDTO(
                        s.getId(), s.getTitulo(), s.getClassificacao(), s.getAvaliacao(), s.getGenero(),
                        s.getAtores(), s.getSinopse(), s.getTotalTemporadas(), s.getPoster()))
                .collect(Collectors.toList());
    }
}
