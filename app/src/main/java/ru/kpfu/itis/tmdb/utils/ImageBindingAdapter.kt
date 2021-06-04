package ru.kpfu.itis.tmdb.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.kpfu.itis.tmdb.R

class ImageBindingAdapter() {

    @BindingAdapter("app:setImageUrl")
    fun loadImage(view: ImageView, url: String?) {
        val options: RequestOptions = RequestOptions()
            .fallback(R.drawable.no_image)
            .placeholder(R.drawable.no_image)
        Glide
            .with(view.context)
            .load("${Constants.IMAGE_LOADING_BASE_URL_200}${url?.let {it}}")
            .apply(options)
            .into(view)
    }
}