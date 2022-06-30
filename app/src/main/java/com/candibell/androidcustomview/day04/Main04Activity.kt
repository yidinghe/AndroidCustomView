package com.candibell.androidcustomview.day04

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import com.candibell.androidcustomview.R

class Main04Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_04)

        val mColorTrackTextView = findViewById<ColorTrackTextView>(R.id.mColorTrackTextView)

        findViewById<Button>(R.id.mLeftToRightBtn).setOnClickListener {
            leftToRight(mColorTrackTextView)
        }

        findViewById<Button>(R.id.mRightToLeftBtn).setOnClickListener {
            rightToLeft(mColorTrackTextView)
        }
    }

    private fun leftToRight(mColorTrackTextView: ColorTrackTextView) {
        mColorTrackTextView.setDirection(ColorTrackDirection.LEFT_TO_RIGHT)
        val valueAnimator = ObjectAnimator.ofFloat(0f, 1f)
        valueAnimator.duration = 2000
        valueAnimator.addUpdateListener { animator ->
            val currentStep = animator.animatedValue as Float
            mColorTrackTextView.setCurrentProgress(currentStep)
        }
        valueAnimator.start()
    }

    private fun rightToLeft(mColorTrackTextView: ColorTrackTextView) {
        mColorTrackTextView.setDirection(ColorTrackDirection.RIGHT_TO_LEFT)
        val valueAnimator = ObjectAnimator.ofFloat(0f, 1f)
        valueAnimator.duration = 2000
        valueAnimator.addUpdateListener { animator ->
            val currentStep = animator.animatedValue as Float
            mColorTrackTextView.setCurrentProgress(currentStep)
        }
        valueAnimator.start()
    }
}