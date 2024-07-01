package org.example.modules;

public class Conversao {
    private ConversaoValores conversaoValores;

    public Conversao(ConversaoValores conversaoValores) {
        this.conversaoValores = conversaoValores;
    }

    @Override
    public String toString() {
        return "##########\n" +
                "Valor convertido: " + conversaoValores.conversion_rate() +
                "\n##########";
    }
}
