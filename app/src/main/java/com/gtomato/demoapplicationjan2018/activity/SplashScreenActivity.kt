package com.gtomato.demoapplicationjan2018.activity

import `interface`.IBasePresenter
import `interface`.IBaseView
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.gtomato.demoapplicationjan2018.R
import com.gtomato.demoapplicationjan2018.engine.SplashScreenPresenter
import com.gtomato.demoapplicationjan2018.helper.SharedPreferenceHelper

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class SplashScreenActivity : BaseActivity(), IBaseView<IBasePresenter> {

    override lateinit var presenter: IBasePresenter

    override fun contentView(): Int {
        return R.layout.activity_splash
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this.getIntent() != null && this.getIntent().getExtras() != null && this.getIntent().getExtras().containsKey(getString(R.string.SHARED_PREFERENCE_IS_REDIRECT))) {
            if (getIntent().getExtras().getBoolean(getString(R.string.SHARED_PREFERENCE_IS_REDIRECT))) {
                SharedPreferenceHelper.sInstance.isRedirect = true
            }
        }

        presenter = SplashScreenPresenter(this)

        Handler().postDelayed({
            //doSomethingHere()
            goToPageAfterSplash()
        }, 2000)
    }

    override fun goToPageAfterSplash() {
        var intent: Intent

        if (SharedPreferenceHelper.sInstance.pageBookmark != -1) {
            if (SharedPreferenceHelper.sInstance.isFinished!!) {
                intent = Intent(this, ResultActivity::class.java)
            } else {
                intent = Intent(this, ReadingActivity::class.java)
            }
        } else {
            intent = Intent(this, QuestionActivity::class.java)
        }

        startActivity(intent)
        finish()
    }

    companion object {

        /**
         * the number of milliseconds to wait after
         * user go into splash screen.
         */
        private val SHOW_SPLASH_SCREEN_DELAY_MILLIS = 2000
    }

}