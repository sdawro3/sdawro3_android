package com.sdaacademy.zientara.rafal.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ResourcesActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        imageView = (ImageView) findViewById(R.id.resources_imageView);
    }

    public void setRandomPicture(View view) {
        imageView.setImageResource(R.drawable.ic_pregnant_woman_black_24dp);
    }
}
