package com.candibell.androidcustomview.day07;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import com.candibell.androidcustomview.R;

public class LetterSideBar extends View {
    private Paint mPaint;
    private int textColor = Color.RED;
    private float textSize = 15f;

    private String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
            "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
        textSize = array.getDimensionPixelSize(
                R.styleable.LetterSideBar_letterTextSize,
                sp2px(textSize)
        );
        textColor =
                array.getColor(R.styleable.LetterSideBar_letterTextColor, textColor);
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(textSize);
        mPaint.setColor(textColor);
    }

    private int sp2px(float textSize) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算指定宽度 左右的padding + 字母的宽度(取决于画笔)
        // 高度可以直接获取
        int textWidth = (int) mPaint.measureText("A");
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        int height = MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画26个字母, x绘制在最中间 = 宽度/2 - 文字/2
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / letters.length;

        for (int i = 0; i < letters.length; i++) {
            // 知道每个字幕的中心位置. 1 字母的高度一半. 2 字母高度一半 + 前面字符的高度
            int letterCenterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            // 基线, 基于中心位置
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLineY = letterCenterY + dy;

            int textWidth = (int) mPaint.measureText(letters[i]);
            int x = getWidth() / 2 - textWidth / 2;
            canvas.drawText(letters[i], x, baseLineY, mPaint);
        }
    }
}
