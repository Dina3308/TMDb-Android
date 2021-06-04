package ru.kpfu.itis.tmdb.presentation.search.di

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
import ru.kpfu.itis.tmdb.presentation.search.SearchViewModel

@Module(includes = [ViewModelModule::class])
class SearchModule {
    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideViewModel(
        tmdbService: TmdbService
    ): ViewModel = SearchViewModel(tmdbService)

    @Provides
    fun provideViewModelCreator(fragment: Fragment, viewModelFactory: ViewModelProvider.Factory): SearchViewModel =
        ViewModelProvider(fragment, viewModelFactory).get(SearchViewModel::class.java)
}