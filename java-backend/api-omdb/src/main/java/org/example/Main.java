package org.example;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.excecao.AnoInvalidoException;
import org.example.modelos.Titulo;
import org.example.modelos.TituloOMDB;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner valor = new Scanner(System.in);
        String busca = "";
        List<Titulo> listaDeTitulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme para sua busca: ");
            busca = valor.nextLine();

            if(busca.equalsIgnoreCase("sair")) {
                break;
            }

            String url = "https://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=633c6ea0";
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                TituloOMDB tituloOMDB = gson.fromJson(json, TituloOMDB.class);

                Titulo titulo = new Titulo(tituloOMDB);
                System.out.println(titulo);

                listaDeTitulos.add(titulo);
            } catch (AnoInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        System.out.println(listaDeTitulos);
        FileWriter fileWriter = new FileWriter("filmes.json");
        fileWriter.write(gson.toJson(listaDeTitulos));
        fileWriter.close();

    }
}