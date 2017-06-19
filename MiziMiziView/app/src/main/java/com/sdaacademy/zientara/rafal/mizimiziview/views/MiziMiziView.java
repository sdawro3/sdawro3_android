package com.sdaacademy.zientara.rafal.mizimiziview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.sdaacademy.zientara.rafal.mizimiziview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: document your custom view class.
 */
public class MiziMiziView extends View implements View.OnTouchListener {
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Paint paint;
    private float touchX;
    private float touchY;
    private float startX;
    private float startY;

    private List<Point> points = new ArrayList<>();


    public MiziMiziView(Context context) {
        super(context);
        init(null, 0);
    }

    public MiziMiziView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public MiziMiziView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.MiziMiziView, defStyle, 0);

        mExampleDimension = a.getDimension(
                R.styleable.MiziMiziView_exampleDimension,
                mExampleDimension);

        a.recycle();

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAlpha(80);
        paint.setStrokeWidth(mExampleDimension/2);
        setOnTouchListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//app:exampleDimension="10dp"
        canvas.drawLine(0, 0, mExampleDimension, mExampleDimension, paint);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2,mExampleDimension, paint);
        canvas.drawCircle(touchX, touchY, mExampleDimension, paint);


        canvas.drawLine(startX, startY, touchX, touchY, paint);

        for (int i = 1; i<points.size(); i++) {
            Point startPoint = points.get(i-1);
            Point endPoint = points.get(i);
            canvas.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y, paint);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        touchX = event.getX();
        touchY = event.getY();
        Log.d("TouchEvent", "x = " + touchX + " y = " + touchY);
        invalidate();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = touchX;
                startY = touchY;
                //// TODO: 19.06.2017 save start point
                break;
            case MotionEvent.ACTION_MOVE:
                Point point = new Point((int) touchX, (int) touchY);
                points.add(point);
                //// TODO: 19.06.2017 move end point
                break;
        }
        return true;
    }
}
