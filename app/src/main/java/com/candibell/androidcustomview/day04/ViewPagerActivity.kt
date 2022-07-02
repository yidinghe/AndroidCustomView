package com.candibell.androidcustomview.day04

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.candibell.androidcustomview.R

class ViewPagerActivity : AppCompatActivity() {

    private val items = listOf<String>("直播", "推荐", "视频", "图片", "段子", "精华")
    private lateinit var mIndicatorContainer: LinearLayout
    private val mIndicators: MutableList<ColorTrackTextView> = mutableListOf()
    private lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_04_view_pager)

        mIndicatorContainer = findViewById(R.id.indicator_view)
        mViewPager = findViewById(R.id.view_pager)
        initIndicator()
        initViewPager()
    }

    private fun initViewPager() {
        mViewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getCount(): Int {
                return items.size
            }

            override fun getItem(position: Int): Fragment {
                return ItemFragment.newInstance(items[position])
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                super.destroyItem(container, position, `object`)
            }

        }
    }

    private fun initIndicator() {
        items.forEach { item ->
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.weight = 1f
            val colorTrackTextView = ColorTrackTextView(this)
            colorTrackTextView.textSize = 20f
            colorTrackTextView.setChangeColor(Color.RED)
            colorTrackTextView.text = item
            colorTrackTextView.layoutParams = params
            mIndicatorContainer.addView(colorTrackTextView)
            mIndicators.add(colorTrackTextView)
        }
    }
}