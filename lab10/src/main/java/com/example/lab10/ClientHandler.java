package com.example.lab10;

import java.io.*;
import java.net.Socket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            while(true) {
                InputStream is = socket.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is));
                String entry = in.readLine();
                System.out.println("Recieve: " + entry);
                Server.displayMessage(entry + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
