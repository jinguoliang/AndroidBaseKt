@file:Suppress("DEPRECATION")

package com.empty.jinux.baselibaray.view.loading

import android.app.ProgressDialog
import android.content.Context
import com.empty.jinux.baselibaray.R
import com.empty.jinux.baselibaray.thread.ThreadPools


fun Context.doTaskWithLoadingDialog(msg: String, delay: Long = 1000, task: () -> Unit) {
    val d = ProgressDialog.show(this, "", msg)
    ThreadPools.postOnUIDelayed(delay) {
        task.invoke()
        ThreadPools.postOnUI {
            d.dismiss()
        }
    }
}

class KLoadingDialog {

}