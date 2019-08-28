package com.example.alarmeaprox;

import android.os.AsyncTask;
import android.util.Log;

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
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ClientConnection(String ip, int port){
        IP = ip;
        PORT = port;
    }

//    @Override
//    protected Object doInBackground(Object[] params) {
//
//    }

    public boolean sendMessage(String message){
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
            socket = new Socket(IP, PORT);
            PrintStream out = new PrintStream(socket.getOutputStream());
            Log.d("ENVIADO ", "ads");
            out.println("teste");
//            closeAll();
        }
        catch (IOException e) {
            Log.d("DEBUG ERROR", e.toString());
        } finally {
            closeAll();
        }
        return null;
    }
}
