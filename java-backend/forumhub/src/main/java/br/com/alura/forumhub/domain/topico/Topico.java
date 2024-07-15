package br.com.alura.forumhub.domain.topico;

import br.com.alura.forumhub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    private String curso;

    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.status = Status.ABERTO;
        this.curso = dados.curso();
        this.dataCriacao = LocalDateTime.now();
    }

    public void atualizaInformacoes(DadosAtualizaTopico dados) {
        if(dados.titulo() != null) this.titulo = dados.titulo();
        if(dados.mensagem() != null) this.mensagem = dados.mensagem();
        if(dados.status() != null) this.status = dados.status();
        if(dados.curso() != null) this.curso = dados.curso();
    }
}
