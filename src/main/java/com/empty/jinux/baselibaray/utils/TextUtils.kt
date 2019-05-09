package com.empty.jinux.baselibaray.utils

import android.content.Context
import android.graphics.Color
import android.support.annotation.DimenRes
import android.text.TextPaint
import android.text.style.BackgroundColorSpan
import android.text.style.MetricAffectingSpan
import org.jetbrains.anko.dimen
import java.util.regex.Pattern


fun String.getFirstLine(limitWidth: Int = 20): String {
    val firstLine = split("\n")[0]
    return firstLine.substring(0 until Math.min(firstLine.length, limitWidth))
}

private val span = BackgroundColorSpan(Color.CYAN)

fun CharSequence.findNewLines(): List<Int> {
    val positions = mutableListOf<Int>()
    val matcher = Pattern.compile("\n").matcher(this)
    while (matcher.find()) {
        positions.add(matcher.start())
    }
    return positions
}

class ParagraphEndLineSpan(val context: Context, @DimenRes private val paragraphEndSpace: Int) : MetricAffectingSpan() {
    override fun updateMeasureState(tp: TextPaint) {
        tp.baselineShift = context.dimen(paragraphEndSpace)
    }

    override fun updateDrawState(tp: TextPaint) {
    }

}

fun String.wordsCount(): Int {
    var inWord = false
    var wordCt = 0
    for (c in this) {
        if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
            if (!inWord) {
                wordCt++
                inWord = true
            }
        } else if (isChineseByBlock(c)) {
            if (!isChinesePunctuation(c)) {
                wordCt++
            }
            inWord = false
        } else {
            inWord = false
        }
    }
    return wordCt
}

fun isChineseByBlock(c: Char): Boolean {
    val ub = Character.UnicodeBlock.of(c)
    return (ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT)
}

fun isChinesePunctuation(c: Char): Boolean {
    val ub = Character.UnicodeBlock.of(c)
    return (ub === Character.UnicodeBlock.GENERAL_PUNCTUATION
            || ub === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
            || ub === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
            || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS)
}