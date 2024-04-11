package com.mycompany.Application;

import com.mycompany.Clients.Cliente;
// import java.net.*;
import java.util.*;
import java.io.*;
import java.util.Scanner;

public class App {
    public static void main( String args[] ) throws IOException {

        String ip;
        int porta;
        Scanner sc = new Scanner(System.in);

        Map<String, Cliente> clientes = new HashMap<>();

        System.out.print("Digite o ip: ");
        ip=sc.next();
        System.out.print("Digite a porta: ");
        porta=sc.nextInt();

        Cliente novoCliente = new Cliente(ip, porta);

        clientes.put(ip, novoCliente);
        clientes.get(ip).conectar();
        sc.close();
    }
}
