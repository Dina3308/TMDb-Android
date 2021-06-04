package ru.kpfu.itis.tmdb.utils

import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides

@Module
class BindingModule {
    @Provides
    @DataBinding
    fun provideImageBindingAdapter(): ImageBindingAdapter{
        return ImageBindingAdapter()
    }
}