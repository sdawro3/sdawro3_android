package com.sdaacademy.zientara.rafal.shakeit;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private TextView accText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        accText = (TextView) findViewById(R.id.main_accText);

        //// TODO: 06.06.2017 init sensor manger and sesnor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        getSystemService(CAMERA_SERVICE);
        getSystemService(FINGERPRINT_SERVICE);
        getSystemService(LOCATION_SERVICE);

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //// TODO: 06.06.2017 unregister listener
        mSensorManager.unregisterListener(MainActivity.this);
        Log.d("Lifecycle", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //// TODO: 06.06.2017 register listener
        mSensorManager.registerListener(MainActivity.this, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("Lifecycle", "onResume");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            String output = "x = " + x + "\ty = " + y + "\tz = " + z;
            Log.d("ACC", output);
            accText.setText(output);


            int r = (int) Math.abs(x) * 25;
            int g = (int) Math.abs(y) * 25;
            int b = (int) Math.abs(z) * 25;

            int color = Color.rgb(r,g,b);
            int negativeColor = Color.rgb(255-r, 255-g, 255-b);

            accText.setBackgroundColor(color);
            accText.setTextColor(negativeColor);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
