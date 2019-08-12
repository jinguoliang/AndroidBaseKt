package com.empty.jinux.baselibaray.utils

import android.content.Context


fun Context.dpToPx(dp: Float): Int {
    val displayMetrics = resources.displayMetrics
    return Math.round(dp * displayMetrics.density)
}

fun Context.pxToDp(px: Int): Float {
    val displayMetrics = resources.displayMetrics
    return px.toFloat() / displayMetrics.density
}

fun Context.getAppVersionName() = packageManager.getPackageInfo(packageName, 0).versionName
fun Context.getAppVersionCode() = packageManager.getPackageInfo(packageName, 0).versionCode