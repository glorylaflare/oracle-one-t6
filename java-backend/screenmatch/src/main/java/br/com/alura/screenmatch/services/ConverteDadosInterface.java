package br.com.alura.screenmatch.services;

public interface ConverteDadosInterface {
    <T> T obterDados(String json, Class<T> classe);
}
