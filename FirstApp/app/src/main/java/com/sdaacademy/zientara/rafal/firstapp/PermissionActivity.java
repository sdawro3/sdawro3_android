package com.sdaacademy.zientara.rafal.firstapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 5;
    private Button askButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        askButton = (Button) findViewById(R.id.permission_askButton);

        askButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_SMS, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Uprawnienia", "Zezwolono na czytanie SMS");
                } else
                    Log.d("Uprawnienia", "Nie ezwolono na czytanie SMS :(");
                break;

        }
    }
}
