package com.empty.jinux.baselibaray.utils

import java.util.*

class CalendarRange(val s: Calendar, val end: Calendar) : Iterable<Calendar>, ClosedRange<Calendar> {
    override fun iterator(): Iterator<Calendar> {
        return CalendarIterator(s, end, 1, Calendar.DAY_OF_MONTH)
    }

    override val endInclusive: Calendar
        get() = end
    override val start: Calendar
        get() = s

}

class CalendarIterator(val first: Calendar, val last: Calendar, val step: Int, val feild: Int) : Iterator<Calendar> {
    private val finalElement = last
    private var hasNext: Boolean = if (step > 0) first <= last else first >= last
    private var next = if (hasNext) first else finalElement

    override fun hasNext(): Boolean = hasNext

    override fun next(): Calendar {
        val value = next
        if (value == finalElement) {
            if (!hasNext) throw kotlin.NoSuchElementException()
            hasNext = false
        } else {
            next = Calendar.getInstance().apply {
                timeInMillis = next.timeInMillis
                add(feild, step)
            }
        }
        return value
    }
}
