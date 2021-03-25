package ru.kpfu.itis.tmdb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.presentation.details.DetailsViewModel
import ru.kpfu.itis.tmdb.presentation.movies.MoviesViewModel
import ru.kpfu.itis.tmdb.presentation.search.SearchViewModel
import ru.kpfu.itis.tmdb.presentation.tv_shows.TvShowsFragment
import ru.kpfu.itis.tmdb.presentation.tv_shows.TvShowsViewModel

class ViewModelFactory(
    private val tmdbService: TmdbService
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(tmdbService) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(TvShowsViewModel::class.java) -> {
                TvShowsViewModel(tmdbService) as? T
                    ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(tmdbService) as? T
                        ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> {
                DetailsViewModel(tmdbService) as? T
                        ?: throw IllegalArgumentException("Unknown viewmodel class")
            }
            else -> {
                throw IllegalArgumentException("Unknown viewmodel class")
            }
        }
}