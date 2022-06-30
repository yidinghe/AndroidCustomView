package com.candibell.androidcustomview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.candibell.androidcustomview.day03.Main03Activity
import com.candibell.androidcustomview.day04.Main04Activity

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
    }
}