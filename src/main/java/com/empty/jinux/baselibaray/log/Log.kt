package com.empty.jinux.baselibaray.log

import android.util.Log
import com.empty.jinux.baselibaray.BuildConfig

/**
 * Created by Jinux on 2018/2/10.
 *
 * 打印日志
 * 为什么有它：
 * 1. 方便调用，默认 TAG 为 BaseLibrary
 * 2. 添加 Debug 开关，只在debug模式打印
 * 3. 可以调整打印日志的信息格式
 * 4. 打印 Throwable
 */

val DEFAULT_TAG = "BaseLibrary"
val LOG_DEBUG = BuildConfig.DEBUG

fun logi(o: Any, tag: String = DEFAULT_TAG): Unit {
    if (LOG_DEBUG) {
        Log.i(tag, formatMessage(o.toString()))
    }
}

fun logd(o: Any, tag: String = DEFAULT_TAG): Unit {
    if (LOG_DEBUG) {
        Log.d(tag, formatMessage(o.toString()))
    }
}

fun logw(o: Any, tag: String = DEFAULT_TAG): Unit {
    if (LOG_DEBUG) {
        Log.w(tag, formatMessage(o.toString()))
    }
}

fun loge(o: Any, tag: String = DEFAULT_TAG): Unit {
    if (LOG_DEBUG) {
        Log.e(tag, formatMessage(o.toString()))
    }
}

fun logThrowable(e: Throwable, tag: String = DEFAULT_TAG): Unit {
    if (LOG_DEBUG) {
        Log.e(tag, formatMessage(Log.getStackTraceString(e)))
    }
}

private fun formatMessage(org: String): String {
    val threadName = Thread.currentThread().name
    val threadId = Thread.currentThread().id
    val msg = "{$threadName:$threadId} $org"
    return msg
}



