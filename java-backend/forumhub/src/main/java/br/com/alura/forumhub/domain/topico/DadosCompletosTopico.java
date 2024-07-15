package br.com.alura.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosCompletosTopico(String titulo,
                                   String mensagem,
                                   LocalDateTime dataCriacao,
                                   Status status,
                                   String autor,
                                   String curso) {

    public DadosCompletosTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getAutor(), topico.getCurso());
    }
}
