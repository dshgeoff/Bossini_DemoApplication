package com.gtomato.demoapplicationjan2018.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import com.gtomato.demoapplicationjan2018.R
import com.gtomato.demoapplicationjan2018.adapter.ReadingPagerAdapter
import com.gtomato.demoapplicationjan2018.helper.SharedPreferenceHelper
import com.gtomato.demoapplicationjan2018.ui.CustomViewPager
import kotlinx.android.synthetic.main.activity_reading.*

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class ReadingActivity : BaseActivity() {

    private var readingPagerAdapter: ReadingPagerAdapter? = null

    override fun contentView(): Int {
        return R.layout.activity_reading
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        readingPagerAdapter = ReadingPagerAdapter(this)
        vpReadingPager.setAllowedSwipeDirection(CustomViewPager.SwipeDirection.right)
        vpReadingPager.adapter = readingPagerAdapter
        tlCircleIndicator.setupWithViewPager(vpReadingPager)

        if (SharedPreferenceHelper.sInstance.isFirstTime!!) {
            SharedPreferenceHelper.sInstance.pageBookmark = 0
            SharedPreferenceHelper.sInstance.isFirstTime = false
        } else {
            vpReadingPager.setCurrentItem(SharedPreferenceHelper.sInstance.pageBookmark!!, true)
        }

        tvProgressButton.setOnClickListener({
            val lastIndex = readingPagerAdapter!!.count - 1
            if (vpReadingPager.currentItem == lastIndex) {
                intent = Intent(this, ResultActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                SharedPreferenceHelper.sInstance.isSkippedReading = true
                vpReadingPager.setCurrentItem(lastIndex, true)
            }
        })
        vpReadingPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                SharedPreferenceHelper.sInstance.pageBookmark = position
                val lastIndex: Int = readingPagerAdapter!!.count - 1
                if (position == lastIndex) {
                    tvProgressButton.setText(R.string.general_done)
                }
            }

            override fun onPageSelected(position: Int) {

            }

        })
    }
}