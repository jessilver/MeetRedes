package com.mycompany.testesdeconexao.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String clientName;

    public ClientHandler(Socket socket) throws InterruptedException {
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
    
            while (!socket.isClosed()) {
                message = input.readLine();
                if (message != null && message.startsWith("TEXT: ")) {
                    System.out.println(clientName + ": " + message.substring(6));
                    Servidor.broadcastMessage(clientName + ": " + message.substring(6), this);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnections();
            Servidor.removeClient(this);
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    public String getClientName() {
        return clientName;
    }

    private void closeConnections() {
        try {
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
