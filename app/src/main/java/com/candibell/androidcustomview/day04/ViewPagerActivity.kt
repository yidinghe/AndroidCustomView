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

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                // position 代表当前位置
                // positionOffSet 代表滚动的百分比 0 - 1
                val left = mIndicators.get(position)
                left.setDirection(ColorTrackDirection.RIGHT_TO_LEFT)
                left.setCurrentProgress(1 - positionOffset)

                if (position == mIndicators.size - 1) {
                    return
                }

                val right = mIndicators.get(position + 1)
                right.setDirection(ColorTrackDirection.LEFT_TO_RIGHT)
                right.setCurrentProgress(positionOffset)
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
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