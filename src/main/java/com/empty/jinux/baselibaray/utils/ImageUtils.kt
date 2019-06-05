package com.empty.jinux.baselibaray.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.empty.jinux.baselibaray.log.loge
import java.io.FileOutputStream

fun Context.getImage(uri: Uri, sampleSize: Int): Bitmap? {
    val inputStream = contentResolver.openInputStream(uri)

    val opt = BitmapFactory.Options().also {
        it.inSampleSize = sampleSize
    }
    return try {
        BitmapFactory.decodeStream(inputStream, null, opt)?.also {
            loge("out = ${opt.outWidth}, ${opt.outHeight}")
            loge("w = ${it.width} h = ${it.height}")
        }
    } catch (e: Exception) {
        null
    }
}

fun Context.getScaleImage(uri: Uri, expectWidth: Int): Bitmap? {
    val oriSize = try {
        getImageSize(uri)
    } catch (e: Exception) {
        return null
    }
    val sampleSize = oriSize.width / expectWidth
    return getImage(uri, sampleSize)
}

fun Context.getImageSize(uri: Uri): Size {
    val opt = BitmapFactory.Options().also {
        it.inJustDecodeBounds = true
    }
    val inputStream = contentResolver.openInputStream(uri)
    BitmapFactory.decodeStream(inputStream, null, opt)
    return Size(opt.outWidth, opt.outHeight)
}

fun Bitmap.compress(path: String): Boolean {
    return compress(Bitmap.CompressFormat.PNG, 100, FileOutputStream(path))
}

data class Size(val width: Int, val height: Int)
