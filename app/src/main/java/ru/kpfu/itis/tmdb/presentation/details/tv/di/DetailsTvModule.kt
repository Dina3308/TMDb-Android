package ru.kpfu.itis.tmdb.presentation.details.tv.di

import  androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.presentation.details.tv.DetailsTvViewModel
import ru.kpfu.itis.tmdb.presentation.di.ViewModelKey
import ru.kpfu.itis.tmdb.presentation.di.ViewModelModule

@Module(includes = [ViewModelModule::class])
class DetailsTvModule {

    @Provides
    @IntoMap
    @ViewModelKey(DetailsTvViewModel::class)
    fun provideViewModel(
        tmdbService: TmdbService
    ): ViewModel = DetailsTvViewModel(tmdbService)

    @Provides
    fun provideViewModelCreator(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory): DetailsTvViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(DetailsTvViewModel::class.java)
}