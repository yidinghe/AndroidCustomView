package com.candibell.androidcustomview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.candibell.androidcustomview.day03.Main03Activity
import com.candibell.androidcustomview.day04.Main04Activity
import com.candibell.androidcustomview.day04.ViewPagerActivity
import com.candibell.androidcustomview.day05.Main05Activity
import com.candibell.androidcustomview.day06.Main06Activity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.mQQStepViewBtn).setOnClickListener {
            val intent = Intent(this, Main03Activity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.mColorTrackViewBtn).setOnClickListener {
            val intent = Intent(this, Main04Activity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.mViewPagerColorTrackViewBtn).setOnClickListener {
            val intent = Intent(this, ViewPagerActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.mProgressBarViewBtn).setOnClickListener {
            val intent = Intent(this, Main05Activity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.mRatingViewBtn).setOnClickListener {
            val intent = Intent(this, Main06Activity::class.java)
            startActivity(intent)
        }
    }
}