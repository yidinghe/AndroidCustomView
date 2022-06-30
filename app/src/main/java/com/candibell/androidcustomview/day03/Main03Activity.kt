package com.candibell.androidcustomview.day03

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.DecelerateInterpolator
import com.candibell.androidcustomview.R

class Main03Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_03)

        val mQQStepView = findViewById<QQStepView>(R.id.mQQStepView)
        mQQStepView.setStepMax(4000)

        val valueAnimator = ObjectAnimator.ofFloat(0f, 3000f)
        valueAnimator.interpolator = object : DecelerateInterpolator() {
            
        }
        valueAnimator.duration = 1000
        valueAnimator.addUpdateListener { animator ->
            val currentStep = animator.animatedValue as Float
            mQQStepView.setCurrentStep(currentStep.toInt())
        }
        valueAnimator.start()
    }
}