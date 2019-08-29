package com.example.alarmeaprox;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnection extends AsyncTask<Void, Void, Void> {
    // DEVE COLOCAR AQUI O IP DA SUA MÁQUINA
    private static String IP = "[IP]";
    private static int PORT = 4500;
    private Socket socket = null;
    private PrintStream out = null;
    private BufferedReader in = null;

    public ClientConnection(String ip, int port){
        IP = ip;
        PORT = port;
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
            out = new PrintStream(socket.getOutputStream());
            Log.d("Criado ", "Socket");
            sendMessage("Alarme");
//            out.println("teste");
//            closeAll();
        }
        catch (IOException e) {
            Log.d("DEBUG ERROR", e.toString());
        } finally {
//            closeAll();
        }
        return null;
    }
}
