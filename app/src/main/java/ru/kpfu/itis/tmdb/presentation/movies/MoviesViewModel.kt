package ru.kpfu.itis.tmdb.presentation.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.data.api.response.MovieResponse
import ru.kpfu.itis.tmdb.data.api.response.Results
import java.io.IOException
import java.net.UnknownHostException

class MoviesViewModel(
    private val tmdbService: TmdbService
) : ViewModel() {

    private val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val popularMovies: MutableLiveData<Result<List<Results>>> = MutableLiveData()
    private val nowPlayingMovies: MutableLiveData<Result<List<Results>>> = MutableLiveData()
    private val topRatedMovies: MutableLiveData<Result<List<Results>>> = MutableLiveData()
    private val upComingMovies: MutableLiveData<Result<List<Results>>> = MutableLiveData()

    init {
        showMovies()
    }

    fun progress(): LiveData<Boolean> = progress

    fun popularMovies(): MutableLiveData<Result<List<Results>>> = popularMovies

    fun nowPlayingMovies(): MutableLiveData<Result<List<Results>>> = nowPlayingMovies

    fun topRatedMovies(): MutableLiveData<Result<List<Results>>> = topRatedMovies

    fun upComingMovies(): MutableLiveData<Result<List<Results>>> = upComingMovies

    private fun showMovies(){
        viewModelScope.launch () {
            try {
                progress.value = true
                popularMovies.value = Result.success(tmdbService.getPopularMovies().results)
            } catch (ex: IOException) {
                popularMovies.value = Result.failure(ex)
            }finally {
                progress.value = false
            }
        }
        viewModelScope.launch {
            try {
                nowPlayingMovies.value = Result.success(tmdbService.getNowPlayingMovies("RU").results)
            } catch (ex: IOException) {
                nowPlayingMovies.value = Result.failure(ex)
            }
        }
        viewModelScope.launch {
            try {
                topRatedMovies.value = Result.success(tmdbService.getTopRatedMovies("RU").results)
            } catch (ex: IOException) {
                topRatedMovies.value = Result.failure(ex)
            }
        }
        viewModelScope.launch {
            try {
                upComingMovies.value = Result.success(tmdbService.getUpcomingMovies("RU").results)
            } catch (ex: IOException) {
                upComingMovies.value = Result.failure(ex)
            }
        }
    }
}