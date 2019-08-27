package com.example.alarmeaprox;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Send extends AsyncTask<Void, Void, String> {

    String dstAddress;
    int dstPort;
    String response = "";
    TextView textResponse;
    Socket socket;

    Send(String addr, int port) {
        this.dstAddress = addr;
        this.dstPort = port;
    }

    @Override
    protected String doInBackground(Void... arg0) {
        try {

            socket = new Socket(this.dstAddress, this.dstPort);

            Log.d("foi", "deu certo");
        } catch (Exception e) {
            Log.d("errado", "except");
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
