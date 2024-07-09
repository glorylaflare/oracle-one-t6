package br.com.alura.screenmatch.service;

public interface ConverteDadosInterface {
    <T> T obterDados(String json, Class<T> classe);
}
