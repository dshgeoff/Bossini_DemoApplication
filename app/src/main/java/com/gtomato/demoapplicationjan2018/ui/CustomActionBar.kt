package com.gtomato.demoapplicationjan2018.ui

import android.R
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.view_custom_actionbar.view.*

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class CustomActionBar(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : Toolbar(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var mListener: ActionBarListener? = null
    private var mStyle: ActionBarStyle? = null

    private var mRotationProgress: ProgressBar? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        imgLeftBtn.setOnClickListener {
            if (mListener != null)
                mListener!!.onLeftBtnClick()
        }

        imgRightBtn.setOnClickListener {
            if (mListener != null)
                mListener!!.onRightBtnClick()
        }

        tvRightBtn.setOnClickListener {
            if (mListener != null)
                mListener!!.onRightBtnClick()
        }


    }


    fun setActionBarStyle(style: ActionBarStyle) {
        mStyle = style
        update()
    }

    fun setListener(listener: ActionBarListener): CustomActionBar {
        mListener = listener
        return this
    }

    fun removeListener(): CustomActionBar {
        mListener = null
        return this
    }

    interface ActionBarListener {
        fun onLeftBtnClick()

        fun onRightBtnClick()
    }


    class ActionBarStyle {
        var leftBtnImage: Int = 0
        var rightBtnImage: Int = 0
        var bgColorRes: Int = 0
        var title: CharSequence = ""
        var rightBtnText: CharSequence? = null

        init {  // default style
            bgColorRes = R.color.transparent
        }
    }

    fun update() {
        if (mStyle == null) {
            visibility = View.GONE
            return
        }
        visibility = View.VISIBLE
        setBackgroundResource(mStyle!!.bgColorRes)

        tvTitle!!.text = mStyle!!.title
        tvTitle!!.visibility = if (TextUtils.isEmpty(mStyle!!.title)) View.GONE else View.VISIBLE

        imgLeftBtn!!.setImageResource(mStyle!!.leftBtnImage)
        imgLeftBtn!!.setColorFilter(Color.WHITE)

        imgRightBtn!!.setImageResource(mStyle!!.rightBtnImage)
        imgRightBtn!!.visibility = if (mStyle!!.rightBtnImage == 0) View.GONE else View.VISIBLE
        imgRightBtn!!.setColorFilter(Color.WHITE)

        tvRightBtn!!.text = mStyle!!.rightBtnText
        tvRightBtn!!.visibility = if (TextUtils.isEmpty(mStyle!!.rightBtnText)) View.GONE else View.VISIBLE
    }


    companion object {
        val BTN_LEFT = 0
        val BTN_RIGHT = 1

        private val PROGRESS_TAG = "progress"
    }
}