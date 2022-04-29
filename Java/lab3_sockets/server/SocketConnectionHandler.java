package org.example.server;

import org.example.common.Consts;
import org.example.common.Message;

import java.io.*;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Arrays;

public class SocketConnectionHandler implements Runnable{
    private final Socket socket;

    SocketConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    //ObjectInputStream input = new ObjectInputStream(socket.getInputStream()
    @Override
    public void run() {
        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream()))   {

            System.out.println("Udalo się nawiazać połączenie");

            output.writeObject(Consts.READY);

            int n = (Integer) input.readObject();

            output.writeObject(Consts.READY_FOR_MESSAGE);


            Message mess = (Message) input.readObject();
            System.out.println(mess.toString());


        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

}
