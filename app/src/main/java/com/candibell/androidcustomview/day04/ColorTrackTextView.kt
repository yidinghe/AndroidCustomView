package com.candibell.androidcustomview.day04

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.candibell.androidcustomview.R

class ColorTrackTextView : AppCompatTextView {

    private var mOriginPaint: Paint? = null
    private var mChangePaint: Paint? = null

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        val array = context!!.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView)
        val originColor =
            array.getColor(R.styleable.ColorTrackTextView_originColor, textColors.defaultColor)
        val changeColor = array.getColor(R.styleable.QQStepView_innerColor, textColors.defaultColor)
        mOriginPaint = constructPaint(originColor)
        mChangePaint = constructPaint(changeColor)
        array.recycle()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {

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