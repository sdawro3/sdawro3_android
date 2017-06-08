package com.sdaacademy.zientara.rafal.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startAnimation(final View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        final Animation animationDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(animationDown);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
