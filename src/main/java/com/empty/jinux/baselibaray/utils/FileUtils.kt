package com.empty.jinux.baselibaray.utils

import java.io.File

fun ensureDirectoryExist(dir: File): File {
    if (dir.exists() && dir.isDirectory) {
        return dir
    }

    if (!dir.exists()) {
        dir.mkdir()
        return dir
    }

    return ensureDirectoryExist(File(dir.absolutePath + "1"))
}