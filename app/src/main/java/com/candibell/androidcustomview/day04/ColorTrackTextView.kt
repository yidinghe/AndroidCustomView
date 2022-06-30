package com.candibell.androidcustomview.day04

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.candibell.androidcustomview.R

class ColorTrackTextView : AppCompatTextView {

    private var mOriginPaint: Paint? = null
    private var mChangePaint: Paint? = null
    private var mCurrentProgress = 0.5f

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        val array = context!!.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView)
        val originColor =
            array.getColor(R.styleable.ColorTrackTextView_originColor, textColors.defaultColor)
        val changeColor =
            array.getColor(R.styleable.ColorTrackTextView_changeColor, textColors.defaultColor)
        mOriginPaint = constructPaint(originColor)
        mChangePaint = constructPaint(changeColor)
        array.recycle()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {

    }

    // 一个文字两种颜色
    // 利用clipRect的API可以裁剪, 左边用一个画笔去画,
    // 根据进度把中间值算出来
    override fun onDraw(canvas: Canvas?) {

        val middle = mCurrentProgress * width

        // 绘制不变色的
        drawText(canvas!!, mOriginPaint!!, 0, middle.toInt())

        // 绘制变色的
        drawText(canvas!!, mChangePaint!!, middle.toInt(), width)
    }

    private fun drawText(canvas: Canvas, paint: Paint, start: Int, end: Int) {
        canvas?.save()
        var rect = Rect(start, 0, end, height)
        canvas?.clipRect(rect)

        val text = text.toString()
        val bounds = Rect()
        paint!!.getTextBounds(text, 0, text.length, bounds)
        val x = width / 2 - bounds.width() / 2
        val fountMetrics = paint!!.fontMetricsInt
        val dy = (fountMetrics.bottom - fountMetrics.top) / 2 - fountMetrics.bottom
        val baseLine = height / 2 + dy
        canvas?.drawText(text, x.toFloat(), baseLine.toFloat(), paint!!)
        canvas?.restore()
    }

    private fun constructPaint(color: Int): Paint {
        val paint = Paint()
        paint.color = color
        paint.isAntiAlias = true
        paint.isDither = true
        paint.textSize = textSize
        return paint
    }
}