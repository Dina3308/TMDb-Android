package ru.kpfu.itis.tmdb.presentation.details.movie.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.presentation.details.movie.DetailsMovieViewModel
import ru.kpfu.itis.tmdb.presentation.di.ViewModelKey
import ru.kpfu.itis.tmdb.presentation.di.ViewModelModule

@Module(includes = [ViewModelModule::class])
class DetailsMovieModule {
    @Provides
    @IntoMap
    @ViewModelKey(DetailsMovieViewModel::class)
    fun provideViewModel(
        tmdbService: TmdbService
    ): ViewModel = DetailsMovieViewModel(tmdbService)

    @Provides
    fun provideViewModelCreator(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory): DetailsMovieViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(DetailsMovieViewModel::class.java)
}