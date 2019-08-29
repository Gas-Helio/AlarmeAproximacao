package com.example.alarmeaprox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.widget.TextView;
import android.hardware.SensorEventListener;
import android.widget.Toast;


import java.io.IOException;
import java.net.Socket;
import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void convectorSalvidor(View v) {
        EditText editText = (EditText) findViewById(R.id.ip_porta);
        String message = editText.getText().toString();
        try {
            String ip = message.split(":")[0];
            Integer port = Integer.parseInt(message.split(":")[1]);

//            Send sd = new Send(ip,port);
            try {
//                ClientConnection Cc = new ClientConnection(ip, port);
//                Cc.execute();
//                Cc.sendMessage("Ola");
                Intent intent = new Intent(this, DisplayMessageActivity.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            } catch (Exception e){
                Toast.makeText(getApplicationContext(), "Falha ao conectar", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Entrada invalida", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
