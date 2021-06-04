package ru.kpfu.itis.tmdb.presentation.multi.di

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
import ru.kpfu.itis.tmdb.presentation.multi.DetailsMultiViewModel

@Module(includes = [ViewModelModule::class])
class DetailsMultiModule {
    @Provides
    @IntoMap
    @ViewModelKey(DetailsMultiViewModel::class)
    fun provideViewModel(
        tmdbService: TmdbService
    ): ViewModel = DetailsMultiViewModel(tmdbService)

    @Provides
    fun provideViewModelCreator(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory): DetailsMultiViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(DetailsMultiViewModel::class.java)
}