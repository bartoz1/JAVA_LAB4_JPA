package org.example.server;

import java.net.Socket;

public class SocketController {
    private final Socket socket;

    public SocketController(Socket socket) {
        this.socket = socket;
    }
}
