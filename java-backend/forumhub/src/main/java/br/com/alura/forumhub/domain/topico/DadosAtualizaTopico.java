package br.com.alura.forumhub.domain.topico;

public record DadosAtualizaTopico(String titulo,
                                  String mensagem,
                                  Status status,
                                  String curso) {
}
