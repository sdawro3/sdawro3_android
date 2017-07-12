package com.sdacademy.zientara.rafal.flowersstore.adapters;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Evil on 12.07.2017.
 */

abstract class DoubleTap implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if(motionEvent.getAction() == MotionEvent.ACTION_UP)
            onFingerUp();

        return true;
    }

    protected abstract void onFingerUp();
}
