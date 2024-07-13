package com.alura.literalura.model.livro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
