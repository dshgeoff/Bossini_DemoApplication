package com.gtomato.demoapplicationjan2018.util

import android.app.AlarmManager
import android.app.IntentService
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.gtomato.demoapplicationjan2018.R
import com.gtomato.demoapplicationjan2018.activity.SplashScreenActivity
import com.gtomato.demoapplicationjan2018.util.AlarmReceiver.Companion.ALARM_REQUEST_CODE
import com.gtomato.demoapplicationjan2018.DemoApplicationJan2018
import com.gtomato.demoapplicationjan2018.activity.AlertDialogReceiver
import java.util.logging.Logger
import android.support.v4.content.LocalBroadcastManager
import android.app.KeyguardManager


/**
 * Created by Geoffrey Ma on 2018-01-16.
 */
class ReminderService : IntentService("reminder_notification") {

    override fun onHandleIntent(intent: Intent?) {
        val title = getString(R.string.notification_title)
        val message = getString(R.string.notification_content)

        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.notification)
                .setAutoCancel(true)
                .setContentTitle(title)

        mBuilder.setContentText(message)
        mBuilder.setTicker(message)
        mBuilder.setWhen(System.currentTimeMillis())

        val notificationIntent = Intent(this, SplashScreenActivity::class.java)

        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        notificationIntent.putExtra(getString(R.string.SHARED_PREFERENCE_IS_REDIRECT), true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent = PendingIntent.getActivity(this, ALARM_REQUEST_CODE, notificationIntent, 0)
        mBuilder.setContentIntent(pendingIntent)
        if (DemoApplicationJan2018.isVisible) {
            val myKM = this.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            if (myKM.inKeyguardRestrictedInputMode()) {
                notificationManager.notify(ALARM_REQUEST_CODE, mBuilder.build())
            } else {
                val intent = Intent(getString(R.string.alert_pop_up))
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
            }
        } else {
            notificationManager.notify(ALARM_REQUEST_CODE, mBuilder.build())
        }
    }

}

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val i = Intent(context, ReminderService::class.java)
        context.startService(i)
    }

    companion object {
        val ALARM_REQUEST_CODE = 100
    }
}
