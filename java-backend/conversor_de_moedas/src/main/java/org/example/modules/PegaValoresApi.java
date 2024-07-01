package org.example.modules;

import org.example.interfaces.Conversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PegaValoresApi implements Conversor {

    @Override
    public String converter(String de, String para) throws IOException, InterruptedException {
        String apiKey = Config.getInstance().getProperty("MY_API_KEY");

        String url = "https://v6.exchangerate-api.com/v6/"+ apiKey +"/pair/"+ de +"/"+ para;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
