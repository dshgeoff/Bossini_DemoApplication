package com.gtomato.demoapplicationjan2018.activity

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gtomato.demoapplicationjan2018.R
import com.gtomato.demoapplicationjan2018.helper.SharedPreferenceHelper
import com.gtomato.demoapplicationjan2018.util.AlarmReceiver
import kotlinx.android.synthetic.main.activity_question.*


/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class QuestionActivity : BaseActivity() {

    override fun contentView(): Int {
        return R.layout.activity_question
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        tvAnswer4.setOnClickListener {
//            val items = arrayOf<CharSequence>("M", "F")
//            val builder = AlertDialog.Builder(this)
//            builder.setItems(items) { _, item ->
//                tvAnswer4.text = items[item]
//            }
//            val alert = builder.create()
//            alert.show()
//        }
//
//        tvAnswer5.setOnClickListener {
//            val items = arrayOf<CharSequence>("18-25", "26-35", ">35")
//            val builder = AlertDialog.Builder(this)
//            builder.setItems(items) { _, item ->
//                tvAnswer5.text = items[item]
//            }
//            val alert = builder.create()
//            alert.show()
//        }

        tvSubmitButton.setOnClickListener {
            if (!tvAnswer1.text.isEmpty()) {
                if (isFormIncomplete()) {
                    setAlarm()
                    SharedPreferenceHelper.sInstance.isEmptyQuestion = true
                }
                intent = Intent(this, ReadingActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val simpleAlert = AlertDialog.Builder(this).create()
                simpleAlert.setMessage(this.getString(R.string.question_1))
                simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, this.getString(R.string.common_confirm), { dialogInterface, _ ->
                })
                simpleAlert.show()
            }
        }
    }

    private fun isFormIncomplete(): Boolean {
        return tvAnswer1.text.isEmpty()
                || tvAnswer2.text.isEmpty()
                || tvAnswer3.text.isEmpty()
//                || tvAnswer4.text == getString(R.string.question_4_hint)
//                || tvAnswer5.text == getString(R.string.question_5_hint)
                || tvAnswer4.text.isEmpty()
                || tvAnswer5.text.isEmpty()
                || tvAnswer6.text.isEmpty()
                || tvAnswer7.text.isEmpty()
    }

    private fun setAlarm() {
        val intent = Intent(applicationContext, AlarmReceiver::class.java)
        val pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.ALARM_REQUEST_CODE,
                intent, PendingIntent.FLAG_ONE_SHOT)
        val firstMillis = System.currentTimeMillis() + (20 * 60 * 1000)
//        val firstMillis = System.currentTimeMillis() + 20000
        val alarm = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarm.set(AlarmManager.RTC_WAKEUP, firstMillis, pIntent)
    }
}