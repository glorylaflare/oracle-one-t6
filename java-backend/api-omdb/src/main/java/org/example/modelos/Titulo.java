package org.example.modelos;

import com.google.gson.annotations.SerializedName;
import org.example.excecao.AnoInvalidoException;

public class Titulo {

    private String nome;
    private int anoDeLancamento;
    private int duracao;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Titulo(TituloOMDB tituloOMDB) {
        this.nome = tituloOMDB.title();

        if (tituloOMDB.year().length() > 4) {
            throw new AnoInvalidoException("O ano é um valor inválido, pois possui mais do que 4 caracteres.");
        }
        this.anoDeLancamento = Integer.parseInt(tituloOMDB.year());
        this.duracao = Integer.parseInt(tituloOMDB.runtime().substring(0,2));
    }

    public String getNome() {
        return nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeLancamento(int anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Titulo{" +
                "nome=" + nome +
                ", anoDeLancamento=" + anoDeLancamento +
                ", duracao=" + duracao + " min" +
                '}';
    }
}
