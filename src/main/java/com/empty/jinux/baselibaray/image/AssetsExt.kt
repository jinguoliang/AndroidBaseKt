package com.empty.jinux.baselibaray.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun Context.getBitmapFromAssets(path: String, sampleSize: Int = 1): Bitmap? {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = false
    options.inSampleSize = sampleSize
    return BitmapFactory.decodeStream(assets.open(path), null, options)
}