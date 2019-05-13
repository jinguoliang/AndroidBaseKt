package com.empty.jinux.baselibaray.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.empty.jinux.baselibaray.log.logw
import java.lang.RuntimeException

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
        isCircleTransform: Boolean = false
    ) {
        if (placeholderResId == -1) {
            logw("maybe you need set default place holder resource id with setDefaultPlaceHolder()")
        }

        image.scaleType = ImageView.ScaleType.CENTER_CROP

        val requestOptions = createRequestOptions(placeholderResId, errorResId)
        if (isCircleTransform) {
            requestOptions.transform(GlideCircleTransformation())
        }

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
}