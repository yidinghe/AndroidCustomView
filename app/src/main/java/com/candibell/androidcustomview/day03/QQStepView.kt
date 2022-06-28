package com.candibell.androidcustomview.day03

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.candibell.androidcustomview.R

class QQStepView : View {

    /*
    * 1. 分析效果
    * 2. 确定自定义属性, 编写attrs.xml
    * 3. 在布局中使用
    * 4. 在自定义view中获取自定义属性
    * 5. onMeasure()
    * 6. 画
    * 7. 其他
    *
    * */

    private var mOuterColor = Color.RED
    private var mInnerColor = Color.BLUE
    private var mBoarderWidth = 20
    private var mStepTextSize = 40
    private var mStepTextColor = 0
    private var mStepMax = 100
    private var mCurrentStep = 50
    private var mOuterPaint: Paint? = null
    private var mInnerPaint: Paint? = null
    private var mTextPaint: Paint? = null

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        val array = context!!.obtainStyledAttributes(attrs, R.styleable.QQStepView)
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor, mOuterColor)
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor)
        mBoarderWidth = array.getDimension(R.styleable.QQStepView_boardWidth, 20f).toInt()
        mStepTextSize =
            array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, mStepTextSize)
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor)
        array.recycle()

        mOuterPaint = Paint()
        mOuterPaint!!.isAntiAlias = true
        mOuterPaint!!.strokeWidth = mBoarderWidth.toFloat()
        mOuterPaint!!.color = mOuterColor
        mOuterPaint!!.strokeCap = Paint.Cap.ROUND
        mOuterPaint!!.style = Paint.Style.STROKE

        mInnerPaint = Paint()
        mInnerPaint!!.isAntiAlias = true
        mInnerPaint!!.strokeWidth = mBoarderWidth.toFloat()
        mInnerPaint!!.color = mStepTextColor
        mInnerPaint!!.strokeCap = Paint.Cap.ROUND
        mInnerPaint!!.style = Paint.Style.STROKE

        mTextPaint = Paint()
        mInnerPaint!!.isAntiAlias = true
        mTextPaint!!.color = mStepTextColor
        mTextPaint!!.textSize = mStepTextSize.toFloat()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {

    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        if (width > height) {
            setMeasuredDimension(height, height)
        } else {
            setMeasuredDimension(width, width)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        val center = width / 2
//        val radius = width / 2 - mBoarderWidth / 2
//        val rectF = RectF(
//            (center - radius).toFloat(),
//            (center - radius).toFloat(),
//            (center + radius).toFloat(),
//            (center + radius).toFloat()
//        )
        val rectF = RectF(
            mBoarderWidth.toFloat() / 2,
            mBoarderWidth.toFloat() / 2,
            (width - mBoarderWidth / 2).toFloat(),
            (height - mBoarderWidth / 2).toFloat()
        )

        if (mStepMax == 0)
            return

        canvas?.drawArc(rectF, 135f, 270f, false, mOuterPaint!!)
        val sweepAngle = mCurrentStep.toFloat() / mStepMax.toFloat()
        canvas?.drawArc(rectF, 135f, sweepAngle * 270, false, mInnerPaint!!)

        val stepText = "$mCurrentStep"
        val textBounds = Rect()
        mTextPaint!!.getTextBounds(stepText, 0, stepText.length, textBounds)
        val dx = width / 2 - textBounds.width() / 2
        val fontMetrics = mTextPaint!!.fontMetricsInt
        val dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
        val baseLine = height / 2 + dy
        canvas?.drawText(stepText, dx.toFloat(), baseLine.toFloat(), mTextPaint!!)
    }
}