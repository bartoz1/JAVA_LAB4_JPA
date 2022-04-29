package org.example.server;

import java.util.Scanner;

public class AppServer {
    public static void main(String[] args) {
        Thread server = new Thread( new IncomingSocketConnectionHandler(9797));
        server.start();

    }
}
