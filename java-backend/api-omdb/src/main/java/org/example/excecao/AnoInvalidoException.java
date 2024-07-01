package org.example.excecao;

public class AnoInvalidoException extends RuntimeException {

    private String mensagem;
    public AnoInvalidoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
