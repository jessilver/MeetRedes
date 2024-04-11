package com.mycompany.Application;

import com.mycompany.Clients.Cliente;
import java.net.*;
import java.util.*;
import java.io.*;

public class App {
    public static void main( String args[] ) throws IOException {

        Map<String, Cliente> clientes = new HashMap<>();

        String ip;
        int porta;
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o ip: ");
        ip=sc.next();
        System.out.print("Digite a porta: ");
        porta=sc.nextInt();
        
        Cliente novoCliente = new Cliente(porta);

        clientes.put(ip, novoCliente);
        clientes.get(ip).conectar(ip);
        sc.close();
    }
}
