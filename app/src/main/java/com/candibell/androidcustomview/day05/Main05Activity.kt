package com.candibell.androidcustomview.day05

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.candibell.androidcustomview.R

class Main05Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_05)

        val mProgressBar = findViewById<ProgressBar>(R.id.mProgressBar)
        mProgressBar.setMax(4000)
        val valueAnimator = ObjectAnimator.ofFloat(0f, 4000f)
        valueAnimator.duration = 2000
        valueAnimator.addUpdateListener { animator ->
            val currentStep = animator.animatedValue as Float
            mProgressBar.setCurrentProgress(currentStep.toInt())
        }
        valueAnimator.start()
    }
}
