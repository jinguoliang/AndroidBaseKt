package com.empty.jinux.baselibaray.image

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.NonNull
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.VideoDecoder
import com.bumptech.glide.request.RequestOptions
import com.empty.jinux.baselibaray.log.logw
import jp.wasabeef.glide.transformations.BlurTransformation
import java.security.MessageDigest


/**
 * Glide 4.0工具类
 */
object GlideImageLoader {
    private var defaultPlaceHolderResId: Int = -1
    private var defaultErrorPlaceHolderResId: Int = -1

    fun setDefaultPlaceHolder(placeholderResId: Int) {
        defaultPlaceHolderResId = placeholderResId
    }

    fun setDefaultErrorPlaceHolder(placeholderResId: Int) {
        defaultErrorPlaceHolderResId = placeholderResId
    }

    fun load(context: Context, obj: Any): RequestBuilder<Drawable> {
        return Glide.with(context).load(obj)
    }

    /**
     * 加载图片
     *
     * @param obj           ：图片来源，可以是url，drawable，文件路径等
     */
    @SuppressLint("CheckResult")
    fun loadIntoView(
        context: Context,
        obj: Any,
        image: ImageView,
        placeholderResId: Int = defaultPlaceHolderResId,
        errorResId: Int = getDefaultErrorResId(),
        isCircleTransform: Boolean = false,
        isBlur: Boolean = false
    ) {
        if (placeholderResId == -1) {
            logw("maybe you need set default place holder resource id with setDefaultPlaceHolder()")
        }

        val requestOptions = createRequestOptions(placeholderResId, errorResId)
        if (isCircleTransform) {
            requestOptions.transform(GlideCircleTransformation())
        }
        if (isBlur) {
            requestOptions.transform(BlurTransformation(15,  3))
        }

        loadIntoView(context, obj, image, requestOptions)
    }

    @SuppressLint("CheckResult")
    fun loadIntoView(
        context: Context,
        obj: Any,
        image: ImageView,
        requestOptions: RequestOptions
    ) {
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(context).load(obj).apply(requestOptions).into(image)
    }

    private fun getDefaultErrorResId(): Int {
        return if (defaultErrorPlaceHolderResId == -1) defaultPlaceHolderResId
        else defaultErrorPlaceHolderResId
    }

    /**
     * 占位图片与错误图片
     */
    private fun createRequestOptions(placeholderResId: Int, errorResId: Int): RequestOptions {
        return RequestOptions()
            .placeholder(placeholderResId)
            .error(errorResId)
    }

    var videoFrameGetter : VideoFrameGetter? = null

    fun loadVideoScreenshot(uri: Uri, frameTimeUs: Long, width: Int, height: Int): Bitmap? {
        return videoFrameGetter?.getFrame(uri, frameTimeUs, width, height)
    }

}

interface VideoFrameGetter {
    fun getFrame(uri: Uri, frameUs: Long, width: Int, height: Int): Bitmap?
}