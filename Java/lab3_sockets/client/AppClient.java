package org.example.client;

import java.util.Scanner;

public class AppClient {
    public static void main(String[] args) {
        System.out.println("Podaj Nnazwe:");
        Scanner scanner = new Scanner(System.in);

        String login = scanner.nextLine();

        Client client = new Client(login);
        System.out.println("Podaj liczbe n: ");
        int number = scanner.nextInt();

        client.sendRequest(number);

    }

}
