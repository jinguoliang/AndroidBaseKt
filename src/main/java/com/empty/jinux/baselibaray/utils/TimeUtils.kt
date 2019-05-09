package com.empty.jinux.baselibaray.utils

import android.os.CountDownTimer
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jingu on 2018/2/21.
 *
 * Time format utils
 */

fun formatTime(t: Long): String {
    val formater = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    return formater.format(t)
}

fun formatDateWithWeekday(t: Long): String {
    val formater = SimpleDateFormat("y/M/d EEEE", Locale.getDefault())
    return formater.format(t)
}

fun Long.weekStartTime(): Long {
    val calendar = toCalendar()
    calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
    calendar.setToDayStart()

    return calendar.timeInMillis
}

fun Long.dayStartTime(): Long {
    val calendar = toCalendar()
    calendar.setToDayStart()

    return calendar.timeInMillis
}

fun Long.formatToWeekOfYear(): String {
    return formatToTimeString("'Week' w")
}

fun Long.formatToTimeString(format: String): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}

fun Long.formatBackupDate(): String {
    return formatToTimeString("yyyy-MM-dd_HH:mm:ss")
}

fun Long.formatToWeekday(): String {
    return formatToTimeString("E")
}

fun Long.formatToTime(): String {
    return formatToTimeString("H:mm")
}

fun Long.formatToDay(): String {
    return formatToTimeString("M-d")
}

fun today(): Calendar {
    return Calendar.getInstance().setToDayStart()
}

fun Calendar.setToDayStart(): Calendar {
    set(Calendar.HOUR_OF_DAY, 0)
    set(Calendar.MINUTE, 0)
    set(Calendar.SECOND, 0)
    set(Calendar.MILLISECOND, 0)
    return this
}

fun Calendar.toStringPretty(): String {
    return "${get(Calendar.YEAR)}年${get(Calendar.MONTH) + 1}月${get(Calendar.DAY_OF_MONTH)}日 "
}

fun Long.toCalendar(): Calendar {
    return Calendar.getInstance().also { it.timeInMillis = this }
}

operator fun Calendar.rangeTo(end: Calendar): CalendarRange {
    return CalendarRange(this, end)
}

abstract class CountDownTimer(millisInFuture: Long, countDownInterval: Long)
    : CountDownTimer(millisInFuture, countDownInterval) {
    companion object {
        fun countDownToDo(
                delay: Long,
                interval: Long = delay,
                onTick: ((Long) -> Unit)? = null,
                onEnd: () -> Unit
        ): CountDownTimer {
            return object : CountDownTimer(delay, interval) {
                override fun onFinish() {
                    onEnd()
                }

                override fun onTick(millisUntilFinished: Long) {
                    onTick?.invoke(millisUntilFinished)
                }
            }.also { it.start() }
        }
    }
}






