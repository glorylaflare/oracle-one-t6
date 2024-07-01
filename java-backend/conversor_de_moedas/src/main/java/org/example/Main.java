package org.example;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.interfaces.Conversor;
import org.example.modules.Config;
import org.example.modules.Conversao;
import org.example.modules.ConversaoValores;
import org.example.modules.PegaValoresApi;
import org.example.services.ConversorService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        //ARS, AUD, BRL, CNY, EUR, GBP, JPY, USD
        System.out.println("Insira um valor correspondente para ser convertido: " +
                "ARS || " +
                "AUD || " +
                "BRL || " +
                "CNY || " +
                "EUR || " +
                "GBP || " +
                "JPY || " +
                "USD");
        String valor1 = scanner.nextLine();
        System.out.println("Insira um valor correspondente para ser converter: " +
                "ARS || " +
                "AUD || " +
                "BRL || " +
                "CNY || " +
                "EUR || " +
                "GBP || " +
                "JPY || " +
                "USD");
        String valor2 = scanner.nextLine();

        Conversor converter = new PegaValoresApi();
        ConversorService conversorService = new ConversorService(converter);
        Conversao conversao = conversorService.converter(valor1, valor2);

        System.out.println(conversao);
    }
}