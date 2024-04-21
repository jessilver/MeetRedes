package com.mycompany.testesdeconexao.server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static List<ClientHandler> clientHandlers = new ArrayList<>();

    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Servidor à escuta na porta 8080...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nova conexão estabelecida com o cliente!");
            
            ClientHandler clientHandler = new ClientHandler(clientSocket);
            clientHandlers.add(clientHandler);

            new Thread(clientHandler).start();
        }
    }

    public static void broadcastMessage(String message, ClientHandler sender) {
        List<ClientHandler> clients = new ArrayList<>(clientHandlers);
        clients.remove(sender);
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
    

    public static void removeClient(ClientHandler clientHandler) {
        clientHandlers.remove(clientHandler);
    }

    public static String getConnectedClients() {
        StringBuilder clientsList = new StringBuilder();
        for (ClientHandler client : clientHandlers) {
            clientsList.append(client.getClientName()).append(", ");
        }
        return clientsList.toString();
    }
}