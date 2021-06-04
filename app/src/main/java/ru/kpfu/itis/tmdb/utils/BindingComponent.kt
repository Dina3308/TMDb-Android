package ru.kpfu.itis.tmdb.utils

import androidx.databinding.DataBindingComponent
import dagger.Component
import dagger.Subcomponent
import ru.kpfu.itis.tmdb.presentation.di.AppComponent

@DataBinding
@Subcomponent(modules = [BindingModule::class])
interface BindingComponent : DataBindingComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BindingComponent
    }

    override fun getImageBindingAdapter(): ImageBindingAdapter
}