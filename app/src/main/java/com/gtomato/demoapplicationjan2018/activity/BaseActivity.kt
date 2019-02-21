package com.gtomato.demoapplicationjan2018.activity

import android.app.AlertDialog
import android.app.FragmentManager
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import com.gtomato.demoapplicationjan2018.DemoApplicationJan2018
import com.gtomato.demoapplicationjan2018.R
import com.gtomato.demoapplicationjan2018.dialog.AlertDialogFragment
import com.gtomato.demoapplicationjan2018.ui.CustomActionBar
import com.gtomato.demoapplicationjan2018.util.DialogPresenter
import com.gtomato.demoapplicationjan2018.util.ProgressDialogUtil
import kotlinx.android.synthetic.main.view_custom_actionbar.*
import java.util.concurrent.atomic.AtomicInteger


abstract class BaseActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    var mProgressDialog: ProgressDialog? = null
    protected abstract fun contentView(): Int
    var alertBroadcastReceiver: AlertDialogReceiver? = null

    private val mLoadingCount = AtomicInteger(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        setSupportActionBar(getToolbar())
    }

    open fun getToolbar(): CustomActionBar? {
        return toolbar
    }

    fun setLoadingIndicator(active: Boolean) {
        if (active)
            showLoading()
        else
            hideLoading()
    }

    override fun onResume() {
        super.onResume()
        DemoApplicationJan2018.isVisible = true
        alertBroadcastReceiver = AlertDialogReceiver(this)
        val lbm = LocalBroadcastManager.getInstance(this)
        lbm.registerReceiver(alertBroadcastReceiver, IntentFilter(getString(R.string.alert_pop_up)))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(alertBroadcastReceiver)
    }

    val isActive: Boolean
        get() = true

    fun onSessionExpired() {
//        TODO("not implemented")
    }

    override fun onBackStackChanged() {
        getToolbar()?.update()
    }

    fun showLoading() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialogUtil.createProgressDialog(this)
        }
        if (!mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
        mProgressDialog = null
    }

    fun displayAlertMessage(msg: String) {
        displayAlertMessage(getString(R.string.error_general), msg)
    }

    fun displayAlertMessage(title: String, desc: String) {
        displayAlertMessage(title, desc, getString(R.string.common_confirm))
    }

    fun displayAlertMessage(title: String, desc: String, buttonText: String) {
        displayAlertMessage(title, desc, buttonText, "", true,
                null, null)
    }

    fun displayAlertMessage(title: String, desc: String, cancelable: Boolean,
                            onclickListener: AlertDialogFragment.AlertDialogButtonClickListener) {
        displayAlertMessage(title, desc, getString(R.string.common_confirm), "",
                cancelable, onclickListener, null)
    }

    fun displayAlertMessage(title: String, desc: String, positiveBtn: String, negativeText: String,
                            positiveOnclickListener: AlertDialogFragment.AlertDialogButtonClickListener,
                            negativeOnClickListener: AlertDialogFragment.AlertDialogButtonClickListener?) {
        displayAlertMessage(title, desc, positiveBtn, negativeText,
                false, positiveOnclickListener, negativeOnClickListener)
    }

    fun displayAlertMessage(title: String, desc: String, positiveText: String, negativeText: String,
                            cancelable: Boolean,
                            positiveOnClickListener: AlertDialogFragment.AlertDialogButtonClickListener?,
                            negativeOnClickListener: AlertDialogFragment.AlertDialogButtonClickListener?) {
        DialogPresenter(this@BaseActivity)
                .present(AlertDialogFragment()
                        .setTitle(title)
                        .setContent(desc)
                        .setPositiveBtnText(positiveText)
                        .setPositiveBtnOnclickListener(positiveOnClickListener)
                        .setNegativeBtnText(negativeText)
                        .setNegativeBtnOnclickListener(negativeOnClickListener),
                        cancelable)
    }
}

open class AlertDialogReceiver(var activity: BaseActivity) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
//        val simpleAlert = AlertDialog.Builder(activity).create()
//        simpleAlert.setTitle(context.getString(R.string.notification_title))
//        simpleAlert.setMessage(context.getString(R.string.notification_content))
//        simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.common_confirm), { dialogInterface, _ ->
//        })
//        simpleAlert.show()
    }

    companion object {
        val ALERT_REQUEST_CODE = 101
    }
}
