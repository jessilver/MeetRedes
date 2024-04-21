package com.mycompany.testesdeconexao.client;

import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private String name;

    public Client(String address, int port,String name) {
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

    public void sendMessage(String message) {
        try {
            output.println("TEXT: "+message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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