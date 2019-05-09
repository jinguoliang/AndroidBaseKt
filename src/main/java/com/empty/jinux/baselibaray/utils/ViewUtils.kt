package com.empty.jinux.baselibaray.utils

import android.content.Context
import android.os.RemoteException
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import com.empty.jinux.baselibaray.log.logd

var View.layoutHeight: Int
    get() = layoutParams.height
    set(h: Int) {
        layoutParams = layoutParams.apply { height = h }
    }

var View.layoutWidth: Int
    get() = layoutParams.width
    set(h: Int) {
        layoutParams = layoutParams.apply { width = h }
    }

var View.layoutBottom: Int
    get() = (layoutParams as FrameLayout.LayoutParams).bottomMargin
    set(h) {
        layoutParams = (layoutParams as FrameLayout.LayoutParams).apply { bottomMargin = h }
    }

var View.layoutTop: Int
    get() = (layoutParams as FrameLayout.LayoutParams).topMargin
    set(top) {
        layoutParams = (layoutParams as FrameLayout.LayoutParams).apply { if (topMargin != top) topMargin = top }
    }

fun View.showInputMethod() {
    val im = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    val showed: Boolean = try {
        im?.showSoftInput(this@showInputMethod, InputMethodManager.SHOW_FORCED)!!
    } catch (e: RemoteException) {
        false
    }

    logd("showInputMethod: $showed")
}

fun View.hideInputMethod() {
    val im = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    im?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun ViewGroup.inflate(@LayoutRes res: Int, attach: Boolean): View {
    return LayoutInflater.from(context).inflate(res, this, attach)
}

fun View.dpToPx(dp: Float) = context.dpToPx(dp)

fun View.pxToDp(px: Int) = context.pxToDp(px)