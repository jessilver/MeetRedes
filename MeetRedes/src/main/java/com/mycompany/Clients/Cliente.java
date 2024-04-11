package com.mycompany.Clients;

import java.net.*;
import java.io.*;
import java.util.UUID;

public class Cliente implements Runnable{

    private final int porta;
    private final Socket socket;
    private final String id;
    private  DataInputStream entrada;
    private  DataOutputStream saida;

    public Cliente(int porta) throws IOException {
        this.socket = new Socket();
        this.porta = porta;
        this.id = UUID.randomUUID().toString();
    }

    public void conectar(String ip) throws IOException {
        try {
            InetSocketAddress endereco = new InetSocketAddress(ip, this.porta);
            this.socket.connect(endereco, 1000);
            System.out.println("Ip: "+ip+" conectado ao servidor na porta: "+porta);
            
            this.entrada = new DataInputStream(socket.getInputStream());
            this.saida = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Nao foi possivel se conectar ao servidor: "+e);
        }
    }

    public void enviarMensagem(String mensagem) throws IOException {
        if (socket.isConnected()) {
            saida.writeUTF(mensagem);
            System.out.println("Cliente " + id + " enviou mensagem: " + mensagem);
        } else {
            System.out.println("Erro: Cliente não conectado ao servidor!");
        }
    }

    
    public String getIp() {
        return this.socket.getInetAddress().getHostAddress();
    }

    public String getId() {
        return this.id;
    }

    public int getPorta() {
        return this.porta;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void closeSocket() throws IOException{
        this.socket.close();
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
