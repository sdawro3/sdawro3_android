package com.sdaacademy.zientara.rafal.colorconverter;

import android.graphics.Color;

/**
 * Created by Evil on 07.06.2017.
 */

public class ColorConverter {

    public static int getColorFromPosition(float x, float y, float z) {
        int r = getColorCanal(x);
        int g = getColorCanal(y);
        int b = getColorCanal(z);

        return Color.rgb(r, g, b);
    }

    private static int getColorCanal(float x) {
        return (int) Math.abs(x) * 25;
    }

    public static int getNegativeColor(int color) {
        int red = 255 - Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        return Color.rgb(255 - red, 255 - green, 255 - blue);
    }
}
