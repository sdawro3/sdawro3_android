package com.sdaacademy.zientara.rafal.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button openHelloActivityButton;
    private Button openPermissionsButton;
    private Button resourcesFunButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle", "onCreate");

        editText = (EditText) findViewById(R.id.editText);
        openHelloActivityButton = (Button) findViewById(R.id.button);
        openPermissionsButton = (Button) findViewById(R.id.main_openPermissionsButton);
        resourcesFunButton = (Button) findViewById(R.id.main_resourcesFunButton);


        openHelloActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(v);
                openHelloActivity();
            }
        });

    }

    public void onButtonClick(View view) {

    }

    private void openPermissionsActivity() {
        Intent intent = new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }

    private void openHelloActivity() {
        String name = editText.getText().toString();
        Intent intent = new Intent(this, MainHello.class);
        intent.putExtra("NAME", name);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Lifecycle", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume");
    }
}
