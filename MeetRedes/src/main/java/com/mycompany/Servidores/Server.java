package com.mycompany.Servidores;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server {

    private static List<ServidorThread> servidorThreads = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Servidor à escuta na porta 8080...");
    
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Nova conexão estabelecida com o cliente!");
            
            ServidorThread servidorThread = new ServidorThread(clientSocket);
            servidorThreads.add(servidorThread);

            new Thread(servidorThread).start();
        }
    }
}
