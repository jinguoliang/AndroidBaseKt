package com.empty.jinux.baselibaray.image

import android.graphics.Bitmap
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory

class VideoFrameModelLoaderFactory : ModelLoaderFactory<VideoFrame, Bitmap> {

    override fun build(unused: MultiModelLoaderFactory): ModelLoader<VideoFrame, Bitmap> {
        return VideoFrameModelLoader()
    }

    override fun teardown() {
        // Do nothing.
    }
}