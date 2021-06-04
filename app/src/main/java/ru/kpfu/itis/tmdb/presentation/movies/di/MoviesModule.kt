package ru.kpfu.itis.tmdb.presentation.movies.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.presentation.di.ViewModelKey
import ru.kpfu.itis.tmdb.presentation.di.ViewModelModule
import ru.kpfu.itis.tmdb.presentation.movies.MoviesViewModel

@Module(includes = [ViewModelModule::class])
class MoviesModule {
    @Provides
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    fun provideViewModel(
        tmdbService: TmdbService
    ): ViewModel = MoviesViewModel(tmdbService)

    @Provides
    fun provideViewModelCreator(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory): MoviesViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(MoviesViewModel::class.java)
}