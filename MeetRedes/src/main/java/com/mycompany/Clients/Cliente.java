package com.mycompany.Clients;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Cliente {

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String name;

    public Cliente(String address, int port,String name) {
        try {
            socket = new Socket(address, port);

            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            this.name = name;
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel se conectar com o servidor", "Aviso", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1); // Exit if connection fails
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel se conectar com o servidor", "Aviso", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            System.exit(1); // Exit if connection fails
        }
    }
  
    public void sendName(String message) {
        try {
            output.println("CONNECT: "+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
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
