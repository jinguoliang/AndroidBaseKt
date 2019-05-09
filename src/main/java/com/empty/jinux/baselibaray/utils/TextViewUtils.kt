package com.empty.jinux.baselibaray.utils

import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.DimenRes

fun TextView.getLineForCursor(): Int {
    return layout.getLineForOffset(selectionEnd)
}

fun EditText.adjustParagraphSpace(@DimenRes paragraphEndSpace: Int) {
    val s = text
    text.getSpans(0, text.length, ParagraphEndLineSpan::class.java).forEach {
        text.removeSpan(it)
    }

    val newLineIndexes = s.findNewLines()
    newLineIndexes.forEach { newLinePos: Int ->
        addParagraphEndSpan(newLinePos, paragraphEndSpace)
    }
}

private fun EditText.addParagraphEndSpan(pos: Int, @DimenRes paragraphEndSpace: Int) {
    if (layout == null) return

    val lineStart = layout.getLineStart(layout.getLineForOffset(pos))
    text.setSpan(ParagraphEndLineSpan(context, paragraphEndSpace), lineStart, pos + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
}

open class TextWatcherAdapter : TextWatcher {
    override fun afterTextChanged(s: Editable?) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}

fun EditText.onTextChangeListener(function: (CharSequence) -> Unit) {
    addTextChangedListener(object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            function(s ?: "")
        }
    })
}