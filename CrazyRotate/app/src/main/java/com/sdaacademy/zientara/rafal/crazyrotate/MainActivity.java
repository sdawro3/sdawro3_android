package com.sdaacademy.zientara.rafal.crazyrotate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private int value;
    private TextView wincyjText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wincyjText = (TextView) findViewById(R.id.wincyjText);
        refreshText();
    }

    public void wincyj(View view) {
        value++;
        refreshText();
    }

    private void refreshText() {
        wincyjText.setText(Integer.toString(value));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("liczba", value);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        value = savedInstanceState.getInt("liczba", -1);
        refreshText();
    }
}
