package com.gtomato.demoapplicationjan2018.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View.VISIBLE
import com.gtomato.demoapplicationjan2018.R
import com.gtomato.demoapplicationjan2018.helper.SharedPreferenceHelper
import com.gtomato.demoapplicationjan2018.util.AlarmReceiver
import kotlinx.android.synthetic.main.activity_result.*

/**
 * Created by Geoffrey Ma on 2018-01-16.
 */
class ResultActivity : BaseActivity() {

    override fun contentView(): Int {
        return R.layout.activity_result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        cancelAlarm()
        SharedPreferenceHelper.sInstance.isFinished = true
        if (SharedPreferenceHelper.sInstance.isRedirect!!) {
            ivRedirectStar.visibility = VISIBLE
        }
        when (SharedPreferenceHelper.sInstance.isEmptyQuestion) {
            true -> when (SharedPreferenceHelper.sInstance.isSkippedReading) {
                true -> rlTransparentContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.demoTransparentPink))
                false -> rlTransparentContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.demoTransparentPurple))
            }
            false -> when (SharedPreferenceHelper.sInstance.isSkippedReading) {
                true -> rlTransparentContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.demoTransparentBlue))
                false -> rlTransparentContainer.setBackgroundColor(ContextCompat.getColor(this, R.color.demoTransparentGreen))
            }
        }
    }

    fun cancelAlarm() {
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.ALARM_REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarm = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarm.cancel(pIntent)
    }
}