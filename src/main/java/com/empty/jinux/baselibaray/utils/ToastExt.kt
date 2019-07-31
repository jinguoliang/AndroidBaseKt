package com.empty.jinux.baselibaray.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showShortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showShortToast(@StringRes resId: Int) {
    showShortToast(resources.getString(resId))
}