package com.sdaacademy.zientara.rafal.firstapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainHello extends AppCompatActivity {

    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hello);

        helloText = (TextView) findViewById(R.id.mainHello_helloText);

        Intent intent = getIntent();
        if(intent!=null) {
            String name = intent.getStringExtra("NAME");
            helloText.setText("Witaj " + name);
            helloText.setText(String.format("Witaj %s", name));
        }


    }
}
