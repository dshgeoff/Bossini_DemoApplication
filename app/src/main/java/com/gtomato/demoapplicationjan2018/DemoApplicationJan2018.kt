package com.gtomato.demoapplicationjan2018

import android.app.Application
import android.content.ComponentCallbacks2
import java.lang.ref.WeakReference

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class DemoApplicationJan2018 : Application() {

    companion object {
        var mInstance: WeakReference<DemoApplicationJan2018>? = null
        var isVisible: Boolean = false

        fun instance(): DemoApplicationJan2018? {
            return mInstance!!.get()
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (mInstance == null)
            mInstance = WeakReference<DemoApplicationJan2018>(this)

    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            isVisible = false
        }
    }
}