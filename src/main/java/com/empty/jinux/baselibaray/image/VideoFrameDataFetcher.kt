package com.empty.jinux.baselibaray.image

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher

class VideoFrameDataFetcher internal constructor(private val model: VideoFrame) : DataFetcher<Bitmap> {

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in Bitmap>) {
        val bitmap = GlideImageLoader
            .loadVideoScreenshot(
                model.uri, model.timeUs,
                model.width, model.height
            )
        callback.onDataReady(bitmap)
    }

    override fun cleanup() {
        // Intentionally empty only because we're not opening an InputStream or another I/O resource!
    }

    override fun cancel() {
        // Intentionally empty.
    }

    override fun getDataClass(): Class<Bitmap> {
        return Bitmap::class.java
    }

    override fun getDataSource(): DataSource {
        return DataSource.LOCAL
    }
}