package com.alura.literalura.service;

public interface ConverteDadosInterface {
    <T> T obterDados(String json, Class<T> classe);
}
