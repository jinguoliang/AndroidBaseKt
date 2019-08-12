package com.empty.jinux.baselibaray.utils

import java.io.File

fun File.getFileSize(excludes: (File) -> Boolean): Long {
    if (excludes(this)) {
        return 0
    }
    when {
        isFile -> return length()
        isDirectory -> return listFiles().fold(0L) { acc, f ->
            acc + f.getFileSize(excludes)
        }
        else -> return 0
    }
}
