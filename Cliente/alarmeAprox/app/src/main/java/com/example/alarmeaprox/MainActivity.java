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


import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    MaskEditUtil mEU = new MaskEditUtil();
    TextView data, ProximitySensor;
    SensorManager mySensorManager;
    Sensor myProximitySensor;
    Send send;
    EditText host, port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = (TextView) findViewById(R.id.data);

        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        if (myProximitySensor == null) {
            ProximitySensor.setText("No Proximity Sensor!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        host = findViewById(R.id.ip_porta);
    }

//    public void sendMessage(View view){
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

    SensorEventListener proximitySensorEventListener
            = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                if (event.values[0] == 0) {
                    data.setText("Near");
//                    Log.d("sensor", "longe");
                } else {
                    data.setText("Away");
//                    Log.d("sensor", "p/erto");
                }
            }
        }
    };

    public void conectarServidor(View v) {
        String host = this.host.getText().toString();
        String ip = host.split(":")[0];
        Integer port = Integer.parseInt(host.split(":")[1]);
        Log.d("logado", host.split(":")[1]);
        send = new Send(ip, port);
        send.execute();

    }

}
