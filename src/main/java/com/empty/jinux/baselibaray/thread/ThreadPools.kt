package com.empty.jinux.baselibaray.thread

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.core.os.postDelayed

object ThreadPools {
    private val thread = HandlerThread("").apply {
        start()
    }
    private val handler = Handler(thread.looper)
    private val mainHandler = Handler(Looper.getMainLooper())

    fun postOnUI(task: () -> Unit): Unit {
        mainHandler.post(task)
    }

    fun postOnUIDelayed(delay: Long, task: () -> Unit): Unit {
        mainHandler.postDelayed(task, delay)
    }

    fun postOnQuene(task: () -> Unit): Unit {
        handler.post(task)
    }

    fun postOnQueue(delay: Long, task: () -> Unit): Unit {
        handler.postDelayed(task, delay)
    }
}