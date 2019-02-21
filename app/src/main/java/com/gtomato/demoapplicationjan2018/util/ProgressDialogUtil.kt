package com.gtomato.demoapplicationjan2018.util

import android.app.ProgressDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import com.gtomato.demoapplicationjan2018.R

/**
 * Created by Geoffrey Ma on 2018-01-15.
 */
class ProgressDialogUtil {

    companion object {

        fun createProgressDialog(context: Context): ProgressDialog {

            val dialog = ProgressDialog(context)

            try {
                dialog.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.dialog_loading)

            return dialog
        }
    }

}