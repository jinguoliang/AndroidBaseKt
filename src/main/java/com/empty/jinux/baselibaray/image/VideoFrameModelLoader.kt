package com.empty.jinux.baselibaray.image

import android.graphics.Bitmap
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.signature.ObjectKey

internal class VideoFrameModelLoader : ModelLoader<VideoFrame, Bitmap> {
    override fun buildLoadData(
        model: VideoFrame,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<Bitmap>? {
        return ModelLoader.LoadData(ObjectKey(model), VideoFrameDataFetcher(model))
    }

    override fun handles(uri: VideoFrame): Boolean {
        return true
    }
}
