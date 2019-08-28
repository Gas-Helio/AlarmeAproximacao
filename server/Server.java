/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveralpx;

/**
 *
 * @author heliojunior
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private ServerSocket server;
    private Socket client;
    private BufferedReader in;
    private String line;
    
    public Server(){
        line = "";
        listenSocket();
    }

    public void listenSocket() {
        // Colocar o porto em escuta (Porto = 4500)
        try {
            server = new ServerSocket(12345);
        } catch (IOException e) {
            System.out.println("Could not listen on port 12345");
            System.exit(-1);
        }
        System.out.println("Listen on Port 4500");
        
        
        //Aceitar cliente
        try {
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4500");
            System.exit(-1);
        }
        System.out.println("Client Accepted");

        //Preparar Buffers
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            System.out.println("Read failed");
            System.exit(-1);
        }
        System.out.println("Buffereds Accepted");

        //Colocar o socket à escuta, à espera de mensagens do cliente
//        while (true) {
            try {
                //Read message from client
                line = in.readLine();
                //Print message from client
                System.out.println("Message from client: " + line);
            } catch (IOException e) {
                System.out.println("Read failed: " + e.toString());
                System.exit(-1);
            }
//        }
    }
}