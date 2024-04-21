package com.mycompany.Servidores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorThread implements Runnable {
    
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String clientName;

    public ServidorThread(Socket socket) {
        this.socket = socket;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

        } catch (IOException e) {
            //...
        }
    }

    @Override
    public void run() {
        
        try {
            String message;
            while ((message = input.readLine()) != null) {
                if (message.startsWith("CONNECT: ")) {
                    clientName = message.substring(9); // Extract the client name
                    System.out.println(clientName + " se conectou.");
                    break;
                }
            }   
        }catch( IOException e ) {
            System.out.println( e );
        }
    }
}
