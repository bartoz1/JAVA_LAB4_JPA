package org.example.client;

import org.example.common.Consts;
import org.example.common.Message;

import java.awt.font.FontRenderContext;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Random;

public class Client {
    private Socket socket;
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public void sendRequest(int n) {

        try (Socket client = new Socket("localhost", 9797)) {
            this.socket = client;

            System.out.println(name + " conneced to server!");

            try(ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream((socket.getOutputStream()))) {

                String response = (String) input.readObject();

                if (!response.equals(Consts.READY)){
                    return;
                }
                output.writeObject(n);

                response = (String) input.readObject();

                if (!response.equals(Consts.READY_FOR_MESSAGE)){
                    return;
                }
                Random rand = new Random();

                int[] tablica = new int[10];
                for (int i=0 ;i<10; i++) {
                    int los = rand.nextInt(n) + 1;
                    tablica[i] = los;

                }
                Message mess = new Message(n, tablica, this.name);
                output.writeObject(mess);


            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }



        } catch (IOException ex) {
            System.err.println(ex);

        }


    }


}
