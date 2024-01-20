package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numeroMaxConvidados = 1500;
        double randNumero = Math.random() * numeroMaxConvidados + 1;
        int numeroConvidados = (int) randNumero;
        int convidados = 0;
        int tentativas = 1;

        System.out.println(numeroConvidados);

        while (convidados != numeroConvidados) {
            convidados = scanner.nextInt();

            if(convidados == numeroConvidados) break;
            else {
                if(convidados > numeroConvidados) System.out.println("O número de convidados é menor!");
                else System.out.println("O número de convidados é maior!");

                tentativas++;
            }
        }

        double pesoDoBolo = (convidados * .15) + .5;
        String palavraTentativas = tentativas > 1 ? "tentativas" : "tentativa";

        System.out.printf("Mandou bem! Você acertou com " + tentativas + " " + palavraTentativas + ". Então iremos precisar de um bolo com " +  pesoDoBolo + " kg.");
    }
}