package com.example.alarmeaprox;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ClientConnection extends AsyncTask<Void, Void, Void> {
    // DEVE COLOCAR AQUI O IP DA SUA MÁQUINA
    private static String IP = "[IP]";
    private static int PORT = 4500;
    private Socket socket = null;
    private PrintStream out = null;
    private BufferedReader in = null;
    String mensagem;

    public ClientConnection(String ip, int port){
        IP = ip;
        PORT = port;
        mensagem = null;
    }

    public boolean sendMessage(String message){
        Log.d("Enviando", "Menssagem");
        Log.d("Enviando", message);
        out.println(message);
        return true;
    }

    private void closeAll(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.d("IP", IP);
            Log.d("PORT", String.valueOf(PORT));
            socket = new Socket(IP, PORT);
//            out = new PrintStream(socket.getOutputStream());
//            Log.d("Criado ", "Socket");

            while(true){
                OutputStream os = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);

                while (!isCancelled()) {
                    TimeUnit.SECONDS.sleep(3);
                    // Gerando Arraylist com os valores do sensor
                    ArrayList<String> mandar = new ArrayList<String>();
                    mandar.add(mensagem);

                    oos.writeObject(mandar);
                }
            }
//            out.println("teste");
//            closeAll();
        }
        catch (IOException e) {
            Log.d("DEBUG ERROR", e.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            closeAll();
        }
        return null;
    }
}
