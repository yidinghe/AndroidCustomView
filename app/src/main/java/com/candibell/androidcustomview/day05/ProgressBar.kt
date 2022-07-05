package com.candibell.androidcustomview.day05

import android.content.Context
import kotlin.jvm.JvmOverloads
import android.view.View.MeasureSpec
import android.graphics.Paint.FontMetricsInt
import android.util.TypedValue
import kotlin.jvm.Synchronized
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.candibell.androidcustomview.R

class ProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mInnerBackground = Color.RED
    private var mOuterBackground = Color.RED
    private var mRoundWidth = 10
    private var mProgressTextSize = 15f
    private var mProgressTextColor = Color.RED
    private val mInnerPaint: Paint
    private val mOuterPaint: Paint
    private val mTextPaint: Paint
    private var mMax = 100
    private var mProgress = 30
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 只保证是正方形
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(Math.min(width, height), Math.min(width, height))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val center = width / 2
        canvas.drawCircle(
            center.toFloat(),
            center.toFloat(),
            (center - mRoundWidth / 2).toFloat(),
            mInnerPaint
        )
        if (mProgress == 0) {
            return
        }
        val percent = mProgress.toFloat() / mMax
        val rect = RectF(
            (mRoundWidth / 2).toFloat(),
            (mRoundWidth / 2).toFloat(),
            (width - mRoundWidth / 2).toFloat(),
            (height - mRoundWidth / 2).toFloat()
        )
        canvas.drawArc(rect, 0f, percent * 360, false, mOuterPaint)
        val text: String = "${percent * 100}%"
        val textBounds = Rect()
        mTextPaint.getTextBounds(text, 0, text.length, textBounds)
        val x = width / 2 - textBounds.width() / 2
        val fontMetricsInt = mTextPaint.fontMetricsInt
        val dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom
        val baseLineY = height / 2 + dy
        canvas.drawText(text, x.toFloat(), baseLineY.toFloat(), mTextPaint)
    }

    private fun sp2px(sp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, resources.displayMetrics)
            .toInt()
    }

    private fun dip2Px(dip: Int): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip.toFloat(),
            resources.displayMetrics
        )
    }

    @Synchronized
    fun setMax(max: Int) {
        mMax = max
    }

    @Synchronized
    fun setCurrentProgress(progress: Int) {
        mProgress = progress
        invalidate()
    }

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar)
        mInnerBackground = array.getColor(R.styleable.ProgressBar_innerBackground, mInnerBackground)
        mOuterBackground = array.getColor(R.styleable.ProgressBar_outerBackground, mOuterBackground)
        mRoundWidth = array.getDimension(R.styleable.ProgressBar_roundWidth, dip2Px(10)).toInt()
        mProgressTextSize = array.getDimensionPixelSize(
            R.styleable.ProgressBar_progressTextSize,
            sp2px(mProgressTextSize)
        ).toFloat()
        mProgressTextColor =
            array.getColor(R.styleable.ProgressBar_progressTextColor, mProgressTextColor)
        array.recycle()
        mInnerPaint = Paint()
        mInnerPaint.isAntiAlias = true
        mInnerPaint.color = mInnerBackground
        mInnerPaint.strokeWidth = mRoundWidth.toFloat()
        mInnerPaint.style = Paint.Style.STROKE
        mOuterPaint = Paint()
        mOuterPaint.isAntiAlias = true
        mOuterPaint.color = mOuterBackground
        mOuterPaint.strokeWidth = mRoundWidth.toFloat()
        mOuterPaint.style = Paint.Style.STROKE
        mTextPaint = Paint()
        mTextPaint.isAntiAlias = true
        mTextPaint.color = mProgressTextColor
        mTextPaint.strokeWidth = mProgressTextSize
    }
}