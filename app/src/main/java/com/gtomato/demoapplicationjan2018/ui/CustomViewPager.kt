package com.gtomato.demoapplicationjan2018.ui

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class CustomViewPager : ViewPager {

    private var initialXValue: Float = 0.toFloat()
    private var direction: SwipeDirection? = null

    enum class SwipeDirection {
        all, left, right, none
    }

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (this.IsSwipeAllowed(event)) {
            super.onTouchEvent(event)
        } else false

    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return if (this.IsSwipeAllowed(event)) {
            super.onInterceptTouchEvent(event)
        } else false

    }

    private fun IsSwipeAllowed(event: MotionEvent): Boolean {
        if (this.direction === SwipeDirection.all) return true

        if (direction === SwipeDirection.none)
        //disable any swipe
            return false

        if (event.action == MotionEvent.ACTION_DOWN) {
            initialXValue = event.x
            return true
        }

        if (event.action == MotionEvent.ACTION_MOVE) {
            try {
                val diffX = event.x - initialXValue
                if (diffX > 0 && direction === SwipeDirection.right) {
                    // swipe from left to right detected
                    return false
                } else if (diffX < 0 && direction === SwipeDirection.left) {
                    // swipe from right to left detected
                    return false
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

        }

        return true
    }

    fun setAllowedSwipeDirection(direction: SwipeDirection) {
        this.direction = direction
    }

    fun getItem(i: Int): Int {
        return this.getCurrentItem() + i
    }
}