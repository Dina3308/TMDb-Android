package ru.kpfu.itis.tmdb.presentation.tv_shows.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.presentation.details.BaseViewModel
import ru.kpfu.itis.tmdb.presentation.di.ViewModelKey
import ru.kpfu.itis.tmdb.presentation.di.ViewModelModule
import ru.kpfu.itis.tmdb.presentation.tv_shows.TvShowsViewModel

@Module(includes = [ViewModelModule::class])
class TvShowsModule {

    @Provides
    @IntoMap
    @ViewModelKey(TvShowsViewModel::class)
    fun provideViewModel(
        tmdbService: TmdbService
    ): ViewModel = TvShowsViewModel(tmdbService)

    @Provides
    fun provideViewModelCreator(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory): TvShowsViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(TvShowsViewModel::class.java)
}