package com.candibell.androidcustomview.day05

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads
import android.view.View.MeasureSpec

class ShapeView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mCurrentShape = Shape.CIRCLE
    private var mPaint: Paint? = null
    private var mPath: Path? = null

    init {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
    }

    constructor(context: Context?) : this(context, null) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(Math.min(width, height), Math.min(width, height))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val center = width / 2
        when (mCurrentShape) {
            Shape.CIRCLE -> {
                mPaint!!.color = Color.YELLOW
                canvas.drawCircle(center.toFloat(), center.toFloat(), center.toFloat(), mPaint!!)
            }
            Shape.SQUARE -> {
                mPaint!!.color = Color.BLUE
                canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaint!!)
            }
            Shape.TRIANGLE -> {
                mPaint!!.color = Color.RED
                if (mPath == null) {
                    mPath = Path()
                    mPath!!.moveTo((width / 2).toFloat(), 0f)
                    mPath!!.lineTo(0f, ((width / 2) * Math.sqrt(3.0)).toFloat())
                    mPath!!.lineTo(width.toFloat(), ((width / 2) * Math.sqrt(3.0)).toFloat())
                    mPath!!.close()
                }
                canvas.drawPath(mPath!!, mPaint!!)
            }
        }
    }

    fun exchange() {
        mCurrentShape = when (mCurrentShape) {
            Shape.CIRCLE -> Shape.SQUARE
            Shape.SQUARE -> Shape.TRIANGLE
            Shape.TRIANGLE -> Shape.CIRCLE
        }
        invalidate()
    }

    enum class Shape {
        CIRCLE, SQUARE, TRIANGLE
    }
}