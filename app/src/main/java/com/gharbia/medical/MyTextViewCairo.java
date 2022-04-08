package com.gharbia.medical;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

public class MyTextViewCairo extends AppCompatTextView {


    public MyTextViewCairo(Context context) {
        super(context);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "cairo.ttf");
        this.setTypeface(face, Typeface.BOLD);
    }

    public MyTextViewCairo(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "cairo.ttf");
        this.setTypeface(face, Typeface.BOLD);
    }

    public MyTextViewCairo(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face = Typeface.createFromAsset(context.getAssets(), "cairo.ttf");
        this.setTypeface(face, Typeface.BOLD);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

}