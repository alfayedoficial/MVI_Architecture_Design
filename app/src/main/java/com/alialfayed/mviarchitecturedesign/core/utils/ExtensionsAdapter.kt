package com.alialfayed.mviarchitecturedesign.core.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import com.alialfayed.mviarchitecturedesign.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


/**
 * Created by ( Eng Ali Al Fayed)
 * Class do : Adapter Extensions
 * Date 1/1/2021 - 4:59 PM
 */

@BindingAdapter("app:bindString")
fun setBindString(textView: TextView, txtString: String?) {
    if (txtString != null)  textView.text = txtString
}

@BindingAdapter("app:bindImgUrl", "app:bindProgressItem")
fun setBindImage(image: ImageView, imageUrl: String?, progressBar: ProgressBar?) {

    val margeLink = Constants.BASE_URL_API + imageUrl
    Glide.with(image.context)
        .load(margeLink)
        .placeholder(R.drawable.place_holder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean,
            ): Boolean {
                progressBar?.hide()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean,
            ): Boolean {
                progressBar?.hide()
                return false
            }

        })
        .into(image)


}







