package com.alura.literalura.model.autor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    List<Autor> findByAnoNascimentoLessThanAndAnoFalecimentoGreaterThan(int anoNascimento, int anoFalecimento);
}
