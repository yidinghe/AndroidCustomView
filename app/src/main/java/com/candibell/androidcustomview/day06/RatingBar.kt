package com.candibell.androidcustomview.day06

import android.content.Context
import kotlin.jvm.JvmOverloads
import android.graphics.Bitmap
import android.view.MotionEvent
import android.content.res.TypedArray
import com.candibell.androidcustomview.R
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class RatingBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val mStarNormalBitmap: Bitmap
    private val mStarFocusBitmap: Bitmap
    private var mGradeNumber = 5
    private var mCurrentGrade = 0
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // 高度, 一张图片的高度
        val height = mStarNormalBitmap.height
        val width = mStarNormalBitmap.width * mGradeNumber
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until mGradeNumber) {
            // i * 星星的宽度
            val x = i * mStarFocusBitmap.width
            val top = paddingTop
            if (mCurrentGrade > i) {
                canvas.drawBitmap(mStarFocusBitmap, x.toFloat(), top.toFloat(), null)
            } else {
                canvas.drawBitmap(mStarNormalBitmap, x.toFloat(), top.toFloat(), null)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // event.getX() 相对于当前控件的位置. event.getRawX() 获取屏幕的x位置
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                //            case MotionEvent.ACTION_UP: // 抬起
                val moveX = event.x
                var currentGrade = (moveX / mStarNormalBitmap.width + 1).toInt()
                if (currentGrade < 0) {
                    currentGrade = 0
                }
                if (currentGrade > mGradeNumber) {
                    currentGrade = mGradeNumber
                }

                if (currentGrade == mCurrentGrade) {
                    return true;
                }

                mCurrentGrade = currentGrade
                invalidate() //尽量减少onDraw的调用.
            }
        }
        // false不消费, 第一次down false, down以后的事件进不来.
        return true
    }

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.RatingBar)
        val startNormalId = array.getResourceId(R.styleable.RatingBar_starNormal, 0)
        val startFocusId = array.getResourceId(R.styleable.RatingBar_starFocus, 0)
        mGradeNumber = array.getInt(R.styleable.RatingBar_gradeNumber, mGradeNumber)
        mStarNormalBitmap = BitmapFactory.decodeResource(resources, startNormalId)
        mStarFocusBitmap = BitmapFactory.decodeResource(resources, startFocusId)
        array.recycle()
    }
}