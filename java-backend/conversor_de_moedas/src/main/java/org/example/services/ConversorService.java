package org.example.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.interfaces.Conversor;
import org.example.modules.Conversao;
import org.example.modules.ConversaoValores;

import java.io.IOException;

public class ConversorService {
    private Conversor conversor;

    public ConversorService(Conversor conversor) {
        this.conversor = conversor;
    }

    public Conversao converter(String de, String para) throws IOException, InterruptedException {
        String json = conversor.converter(de, para);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        ConversaoValores conversaoValores = gson.fromJson(json, ConversaoValores.class);
        return new Conversao(conversaoValores);
    }
}
