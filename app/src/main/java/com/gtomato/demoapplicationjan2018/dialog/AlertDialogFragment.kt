package com.gtomato.demoapplicationjan2018.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gtomato.demoapplicationjan2018.R
import kotlinx.android.synthetic.main.dialog_fragment_alert.*
import java.util.regex.Pattern

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class AlertDialogFragment : DialogFragment() {

    var mTitle: CharSequence? = null
    var mContent: CharSequence? = null
    var mPositiveBtnText: CharSequence? = null
    var mNegativeBtnText: CharSequence? = null

    var mOnPositiveClickListener: AlertDialogButtonClickListener? = null
    var mOnNegativeClickListener: AlertDialogButtonClickListener? = null

    fun setTitle(title: String): AlertDialogFragment {
        mTitle = title
        return this
    }

    fun setContent(content: String): AlertDialogFragment {
        mContent = content
        return this
    }

    fun setPositiveBtnText(btnText: String): AlertDialogFragment {
        mPositiveBtnText = btnText
        return this
    }

    fun setNegativeBtnText(btnText: String): AlertDialogFragment {
        mNegativeBtnText = btnText
        return this
    }

    fun setPositiveBtnOnclickListener(onclickListener: AlertDialogButtonClickListener?): AlertDialogFragment {
        mOnPositiveClickListener = onclickListener
        return this
    }

    fun setNegativeBtnOnclickListener(onclickListener: AlertDialogButtonClickListener?): AlertDialogFragment {
        mOnNegativeClickListener = onclickListener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AlertDialogFragment)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = LayoutInflater.from(activity).inflate(R.layout.dialog_fragment_alert, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvTitle.text = mTitle

        if (!mContent.isNullOrEmpty()) {
            val pattern = Pattern.compile("<.+>")
            val matcher = pattern.matcher(mContent!!)
            if (matcher.find()) {
                tvContent.text = Html.fromHtml(mContent!!.toString())
            } else {
                tvContent.text = mContent
            }
        }

        if (!TextUtils.isEmpty(mPositiveBtnText)) {
            positiveBtn.text = mPositiveBtnText
            positiveBtn.setOnClickListener({ v ->
                if (mOnPositiveClickListener != null) {
                    mOnPositiveClickListener!!.onClick(v)
                }
                dismiss()
            })
        }
        if (!TextUtils.isEmpty(mNegativeBtnText)) {
            negativeBtn.text = mNegativeBtnText
            negativeBtn.setOnClickListener({ v ->
                if (mOnNegativeClickListener != null) {
                    mOnNegativeClickListener!!.onClick(v)
                }
                dismiss()
            })
        }
    }

    interface AlertDialogButtonClickListener {
        fun onClick(view: View)
    }

}