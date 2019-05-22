package com.empty.jinux.baselibaray.image

import android.net.Uri

data class VideoFrame(
    val uri: Uri,
    val timeUs: Long,
    val width: Int,
    val height: Int
)
