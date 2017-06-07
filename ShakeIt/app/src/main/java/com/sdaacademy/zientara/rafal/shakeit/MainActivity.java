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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sdaacademy.zientara.rafal.colorconverter.ColorConverter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public static final String TAG = "Lifecycle";
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
        checkModules();
    }

    private void checkModules() {
        checkCameraService();
        checkVersionFingerService();
        checkLocationService();
        checkGravity();
    }

    private void checkGravity() {
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY) != null)
            gravityText.setText("grawitacja!\n");
        else
            gravityText.setText("Nie ogarniam grawitacji :(\n");
    }

    private void checkLocationService() {
        if (getSystemService(LOCATION_SERVICE) != null)
            locationText.setText("Uslugi lokalizacji sa dostepne!\n");
        else
            locationText.setText("Uslugi lokalizacji sa niedostepne :(\n");
    }

    private void checkVersionFingerService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkFingerService();
        }
    }

    private void checkCameraService() {
        if (getSystemService(CAMERA_SERVICE) != null)
            cameraText.setText("Kamera dostepna!\n");
        else
            cameraText.setText("Kamera niedostepna!\n");
    }

    @OnClick(R.id.main_accText)
    public void showToast() {
        Toast.makeText(this, "Siema!", Toast.LENGTH_SHORT).show();

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
        Log.d(TAG, "onPause");
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
            setAccColors(x, y, z);
        }
    }

    private void setAccColors(float x, float y, float z) {
        int color = ColorConverter.getColorFromPosition(x,y,z);
        int negativeColor = ColorConverter.getNegativeColor(color);

        accText.setBackgroundColor(color);
        accText.setTextColor(negativeColor);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
