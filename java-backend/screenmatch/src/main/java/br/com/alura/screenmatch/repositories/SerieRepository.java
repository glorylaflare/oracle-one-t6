package br.com.alura.screenmatch.repositories;

import br.com.alura.screenmatch.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
}
