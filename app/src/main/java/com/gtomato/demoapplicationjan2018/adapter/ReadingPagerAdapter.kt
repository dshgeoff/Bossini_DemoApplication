package com.gtomato.demoapplicationjan2018.adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.gtomato.demoapplicationjan2018.R
import kotlinx.android.synthetic.main.view_reading_page.view.*


/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class ReadingPagerAdapter(activity: FragmentActivity) : PagerAdapter() {

    var mInflator: LayoutInflater = activity.baseContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    var pageImageArray = intArrayOf(
            R.drawable.client_creativeproposal_template001,
            R.drawable.client_creativeproposal_template002,
            R.drawable.client_creativeproposal_template003,
            R.drawable.client_creativeproposal_template004,
            R.drawable.client_creativeproposal_template005,
            R.drawable.client_creativeproposal_template006,
            R.drawable.client_creativeproposal_template007,
            R.drawable.client_creativeproposal_template008,
            R.drawable.client_creativeproposal_template009,
            R.drawable.client_creativeproposal_template010,
            R.drawable.client_creativeproposal_template011,
            R.drawable.client_creativeproposal_template012,
            R.drawable.client_creativeproposal_template013,
            R.drawable.client_creativeproposal_template014,
            R.drawable.client_creativeproposal_template015,
            R.drawable.client_creativeproposal_template016,
            R.drawable.client_creativeproposal_template017,
            R.drawable.client_creativeproposal_template018,
            R.drawable.client_creativeproposal_template019)

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return pageImageArray.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        var pageLayout: View = mInflator.inflate(R.layout.view_reading_page, container, false)
        pageLayout.ivReadingPage.setImageResource(pageImageArray[position])
        container!!.addView(pageLayout)
        return pageLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}
