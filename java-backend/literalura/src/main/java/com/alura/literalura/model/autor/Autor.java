package com.alura.literalura.model.autor;

import com.alura.literalura.model.livro.Livro;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Autor: ").append(nome).append("\n");
        sb.append("Ano de nascimento: ").append(anoNascimento).append("\n");
        sb.append("Ano de falecimento: ").append(anoFalecimento).append("\n");
        sb.append("Livros: ").append(livros.stream().map(Livro::getTitulo).collect(Collectors.toList())).append("\n");
        return sb.toString();
    }
}
