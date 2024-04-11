package com.mycompany.Clients;

import java.net.*;
import java.io.*;

public class Cliente {

    private String ip;
    private int porta;
    private Socket socket;

    public Cliente(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }

    public void conectar() throws IOException {
        try {
            InetSocketAddress endereco = new InetSocketAddress(this.ip, this.porta);
            socket = new Socket();
            socket.connect(endereco, 1000);
            System.out.println("Ip: "+ip+" conectado ao servidor na porta: "+porta);
        } catch (IOException e) {
            System.out.println("Nao foi possivel se conectar ao servidor: "+e);
        }
    }

    
    public String getIp() {
        return this.ip;
    }

    public int getPorta() {
        return this.porta;
    }

    public Socket getSocket() {
        return this.socket;
    }
}
