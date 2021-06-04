package ru.kpfu.itis.tmdb

import android.app.Application
import androidx.databinding.DataBindingUtil
import ru.kpfu.itis.tmdb.presentation.di.AppComponent
import ru.kpfu.itis.tmdb.utils.BindingComponent


class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        val bindingComponent = DaggerBindingComponent.builder()
            .appComponent(appComponent)
            .build()
        DataBindingUtil.setDefaultComponent(bindingComponent)
    }

}