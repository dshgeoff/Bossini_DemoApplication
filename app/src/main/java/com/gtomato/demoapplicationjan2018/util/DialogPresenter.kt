package com.gtomato.demoapplicationjan2018.util

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class DialogPresenter {

    private var mActivity: FragmentActivity? = null
    private var mDialog: DialogFragment? = null

    constructor(activity: FragmentActivity) {
        mActivity = activity;
    }

    fun <D : DialogFragment> present(dialog: D) {
        present(dialog, true)
    }

    fun <D : DialogFragment> present(dialog: D, cancelable: Boolean) {
        mDialog = dialog
        mDialog!!.setCancelable(cancelable)
        performPresent()
    }

    @Throws(IllegalStateException::class)
    fun <D : DialogFragment> softPresent(dialog: D, cancelable: Boolean) {
        mDialog = dialog
        mDialog!!.setCancelable(cancelable)
        mDialog!!.show(mActivity!!.supportFragmentManager, "dialog " + mDialog!!.javaClass.getSimpleName())
    }

    private fun performPresent() {
        if (mActivity!!.supportFragmentManager.findFragmentByTag("dialog " + mDialog!!.javaClass.getSimpleName()) == null)
            try {
                mDialog!!.show(mActivity!!.supportFragmentManager, "dialog " + mDialog!!.javaClass.getSimpleName())
            } catch (e: IllegalStateException) {

            }

    }

}