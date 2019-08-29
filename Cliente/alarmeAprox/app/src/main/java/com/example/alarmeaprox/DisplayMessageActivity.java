package com.example.alarmeaprox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Socket;

public class DisplayMessageActivity extends AppCompatActivity implements SensorEventListener {

    public static int onf = 0;
    private static TextView textView;
    private SensorManager mSensorManager;
    private Sensor mProximity;
    private static final int SENSOR_SENSITIVITY = 4;
    private static Socket socket;
    private static Send send;
//    private static ClientConnection cC;
    private static String ip;
    private static int port;
    ClientConnection cC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String host = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        ip = host.split(":")[0];
        port = Integer.parseInt(host.split(":")[1]);

        textView = findViewById(R.id.button);
        textView.setText(R.string.off);
        onf = 0;
        cC = new ClientConnection(ip, port);
        cC.execute();
    }

    public void conectar(View v){
        if (onf == 0){
            textView.setText(R.string.on);
            onf = 1;
        } else {
            textView.setText(R.string.off);
            onf = 0;
        }
    }

    public static void onoff() {
        if (onf == 0){
            textView.setText(R.string.on);
            onf = 1;
        } else {
            textView.setText(R.string.off);
            onf = 0;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (onf == 1) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] >= -SENSOR_SENSITIVITY && event.values[0] <= SENSOR_SENSITIVITY) {
                    //near
                    Toast.makeText(getApplicationContext(), "near", Toast.LENGTH_SHORT).show();
                    try {
                        cC.mensagem = "perto";
                        onoff();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro ao enviar para o servidor", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
