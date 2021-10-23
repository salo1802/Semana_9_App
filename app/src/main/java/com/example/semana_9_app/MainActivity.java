package com.example.semana_9_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import events.OnMessageListener;
import model.Orden;

public class MainActivity extends AppCompatActivity implements OnMessageListener {
    UDPConection udp;
    Orden orden;
    private String ipPc, ip,type, time,ordenString;
    private int port;
    private ImageView a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //6000 si es cel 7000 si es emulador
        port = 6000;
        //poner ip del compu
        ipPc = "192.168.1.121";
        //poner ip del cel
        ip = "192.168.1.105";

        new Thread(()->{
            while (true){

                Calendar hora = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                time = sdf.format(hora.getTime());

            }
        }).start();

        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        d = findViewById(R.id.d);

        udp = new UDPConection();
        udp.setObsever(this);
        udp.start();

        Gson gson = new Gson();


        a.setOnClickListener((v)->{
            type = "1";
        orden = new Orden(time,type);
        orden.setIpFrom(ip);
        orden.setPortFrom(""+port);
       ordenString =  gson.toJson(orden);
       udp.sendMsg(ordenString,ipPc);
        });

        b.setOnClickListener((v)->{
            type = "2";
            orden = new Orden(time,type);
            orden.setIpFrom(ip);
            orden.setPortFrom(""+port);
            ordenString =  gson.toJson(orden);
            udp.sendMsg(ordenString,ipPc);
        });

        c.setOnClickListener((v)->{
            type = "3";
            orden = new Orden(time,type);
            orden.setIpFrom(ip);
            orden.setPortFrom(""+port);
            ordenString =  gson.toJson(orden);
            udp.sendMsg(ordenString,ipPc);
        });

        d.setOnClickListener((v)->{
            type = "4";
            orden = new Orden(time,type);
            orden.setIpFrom(ip);
            orden.setPortFrom(""+port);
            ordenString =  gson.toJson(orden);
            udp.sendMsg(ordenString,ipPc);
        });

    }




    @Override
    public void onOrderReceived() {

        runOnUiThread(()->{
            Toast.makeText(this,"your order has been received",Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public void onOrderDenied() {
        runOnUiThread(()->{
        Toast.makeText(this,"your order has been denied please try again later",Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onOrderReady() {
            runOnUiThread(()->{
        Toast.makeText(this,"your order is ready",Toast.LENGTH_LONG).show();
            });
    }

    public int getPort() {
        return port;
    }


}