package com.example.alarmeaprox;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Send extends AsyncTask<String, Void, Void> {

    private String dstAddress;
    private int dstPort;
    String response = "";
    private Socket socket;

    Send(String addr, int port) {
        this.dstAddress = addr;
        this.dstPort = port;
    }

    public boolean testeSinal(String s) {
        try {
            this.socket = new Socket(this.dstAddress, this.dstPort);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected Void doInBackground(String... strings) {
        try {

            this.socket = new Socket(this.dstAddress, this.dstPort);

            OutputStream outStream = socket.getOutputStream();

            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            Integer cont = 0;
            while (!isCancelled()) {
                TimeUnit.SECONDS.sleep(3);
                // Gerando Arraylist com os valores do sensor
                ArrayList<String> mandar = new ArrayList<String>();
                mandar.add("Olaaaaa");

                oos.writeObject(mandar);
            }

            Log.d("foi", "deu certo");
        } catch (Exception e) {
            Log.d("errado", "except");
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
