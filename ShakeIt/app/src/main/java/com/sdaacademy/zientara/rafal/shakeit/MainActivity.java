package com.sdaacademy.zientara.rafal.shakeit;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    @BindView(R.id.main_accText)
    TextView accText;

    @BindView(R.id.cameraText)
    TextView cameraText;

    @BindView(R.id.fingerText)
    TextView fingerText;

    @BindView(R.id.locationText)
    TextView locationText;

    @BindView(R.id.gravityText)
    TextView gravityText;

    @BindView(R.id.mojaNazwaObiektuId)
    LinearLayout dummyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //// TODO: 06.06.2017 init sensor manger and sesnor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (getSystemService(CAMERA_SERVICE) != null)
            cameraText.setText("Kamera dostepna!\n");
        else
            cameraText.setText("Kamera niedostepna!\n");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkFingerService();
        }


        if (getSystemService(LOCATION_SERVICE) != null)
            locationText.setText("Uslugi lokalizacji sa dostepne!\n");
        else
            locationText.setText("Uslugi lokalizacji sa niedostepne :(\n");

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null)
            gravityText.setText("grawitacja!\n");
        else
            gravityText.setText("Nie ogarniam grawitacji :(\n");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkFingerService() {
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
        if (fingerprintManager != null && fingerprintManager.isHardwareDetected()) {
            fingerText.setText("Czytnik linii papilarnych jest dostepny!\n");
            //use fingerprintManager
        } else
            fingerText.setText("Czytnik linii papilarnych jest niedostepny\n");
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
        mSensorManager.registerListener(MainActivity.this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
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

            int color = Color.rgb(r, g, b);
            int negativeColor = Color.rgb(255 - r, 255 - g, 255 - b);

            accText.setBackgroundColor(color);
            accText.setTextColor(negativeColor);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
