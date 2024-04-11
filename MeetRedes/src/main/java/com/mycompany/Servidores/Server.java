package com.mycompany.Servidores;

import com.mycompany.Clients.Cliente;
//import java.io.*;
import java.net.*;
import java.util.*;
// import javax.media.*;
// import javax.media.protocol.*;
// import javax.media.control.*;

public class Server {
    
    private static final int PORTA = 4321; // Porta para o servidor
    private static final Map<String, Cliente> clientes = new HashMap<>(); // Mapa para armazenar clientes conectados

    public static void main(String[] args) throws Exception {

        try (ServerSocket servidorSocket = new ServerSocket(PORTA)) {
            
            System.out.println("Servidor de Chamada de Vídeo iniciado na porta " + PORTA);

            
            Socket clienteSocket = servidorSocket.accept();
            String ip = clienteSocket.getInetAddress().getHostAddress();
            System.out.println("Cliente conectado: " + ip);

            Cliente cliente = new Cliente(PORTA);
            clientes.put(cliente.getId(), cliente);

            // Iniciar thread para processar o cliente
            //new Thread(cliente).start();
            
        } catch(Exception e){
            System.out.println("Catch: "+e);
        }
    }

//     private static class Cliente implements Runnable {

//         private final Socket socket;
//         private final String id;
//         private final DataInputStream entrada;
//         private final DataOutputStream saida;

//         public Cliente(Socket socket) throws IOException {
//             this.socket = socket;
//             this.id = UUID.randomUUID().toString();
//             this.entrada = new DataInputStream(socket.getInputStream());
//             this.saida = new DataOutputStream(socket.getOutputStream());
//         }

//         public String getId() {
//             return id;
//         }

        // @Override
        // public void run() {
        //     try {
        //         while (true) {

        //             String mensagem = entrada.readUTF();
        //             System.out.println("Cliente " + id + ": " + mensagem);

        //             // Processar a mensagem do cliente (ex: iniciar chamada, enviar vídeo)
        //             if (mensagem.startsWith("INICIAR_CHAMADA")) {
        //                 String idOutroCliente = mensagem.substring(15);
        //                 if (clientes.containsKey(idOutroCliente)) {
        //                     Cliente outroCliente = clientes.get(idOutroCliente);
        //                     outroCliente.saida.writeUTF("INICIAR_CHAMADA#" + id);
        //                 } else {
        //                     saida.writeUTF("ERRO#Cliente não encontrado: " + idOutroCliente);
        //                 }
        //             } else if (mensagem.startsWith("VIDEO#")) {
        //                 // Receber e retransmitir o vídeo do cliente
        //                 byte[] dadosVideo = new byte[1024];
        //                 int bytesLidos;
        //                 while ((bytesLidos = entrada.read(dadosVideo)) != -1) {
        //                     // Enviar dados de vídeo para outros clientes
        //                     for (Cliente outroCliente : clientes.values()) {
        //                         if (!outroCliente.getId().equals(id)) {
        //                             outroCliente.saida.writeUTF("VIDEO#" + id);
        //                             outroCliente.saida.write(dadosVideo, 0, bytesLidos);
        //                         }
        //                     }
        //                 }
        //             } else {
        //                 // Tratar outras mensagens do cliente
        //                 System.out.println("Mensagem não reconhecida: " + mensagem);
        //             }
        //         }
        //     } catch (IOException e) {
        //         System.err.println("Erro de comunicação com cliente: " + id + " - " + e.getMessage());
        //         clientes.remove(id);
        //         try {
        //             socket.close();
        //         } catch (IOException e2) {
        //             e2.printStackTrace();
        //         }
        //     }
        // }
//     }
}
