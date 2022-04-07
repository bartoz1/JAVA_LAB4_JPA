package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IncomingSocketConnectionHandler implements Runnable{

    private final ServerSocket server;

    public IncomingSocketConnectionHandler(int port) {
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(1000);

        } catch (IOException e) {
            System.out.println("Can't use that port");
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        System.out.println("Starting HTTP listener.");
        while (!Thread.interrupted()) {
            try {
                Socket socket = server.accept();
                new Thread(new SocketConnectionHandler(socket)).start();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
        System.out.println("Stopping HTTP listener.");
    }
}
