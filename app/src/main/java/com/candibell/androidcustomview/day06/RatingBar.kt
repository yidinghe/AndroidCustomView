package com.candibell.androidcustomview.day06;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RatingBar extends View {

    private Bitmap mStarNormalBitmap, mStarFocusBitmap;
    private int mGradeNumber = 5;

    public RatingBar(Context context) {
        this(context, null);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
