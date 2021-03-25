package ru.kpfu.itis.tmdb.presentation.tv_shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.data.api.response.Results
import java.io.IOException

class TvShowsViewModel(
    private val tmdbService: TmdbService
) : ViewModel(){

    private val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val popularTvShows: MutableLiveData<Result<List<Results>>> = MutableLiveData()
    private val airingTodayTvShows: MutableLiveData<Result<List<Results>>> = MutableLiveData()
    private val topRatedTvShows: MutableLiveData<Result<List<Results>>> = MutableLiveData()
    private val onTheAirTvShows: MutableLiveData<Result<List<Results>>> = MutableLiveData()

    init {
        showTv()
    }

    fun progress(): LiveData<Boolean> = progress

    fun popularTvShows(): MutableLiveData<Result<List<Results>>> = popularTvShows

    fun airingTodayTvShows(): MutableLiveData<Result<List<Results>>> = airingTodayTvShows

    fun topRatedTvShows(): MutableLiveData<Result<List<Results>>> = topRatedTvShows

    fun onTheAirTvShows(): MutableLiveData<Result<List<Results>>> = onTheAirTvShows

    private fun showTv(){
        progress.value = true
        viewModelScope.launch () {
            try {
                popularTvShows.value = Result.success(tmdbService.getTvPopular().results)
            } catch (ex: IOException) {
                popularTvShows.value = Result.failure(ex)
            }finally {
                progress.value = false
            }
        }
        viewModelScope.launch {
            try {
                airingTodayTvShows.value = Result.success(tmdbService.getTvAiringToday().results)
            } catch (ex: IOException) {
                airingTodayTvShows.value = Result.failure(ex)
            }
        }
        viewModelScope.launch {
            try {
                topRatedTvShows.value = Result.success(tmdbService.getTvTopRated().results)
            } catch (ex: IOException) {
                topRatedTvShows.value = Result.failure(ex)
            }
        }
        viewModelScope.launch {
            try {
                onTheAirTvShows.value = Result.success(tmdbService.getTvOnTheAir().results)
            } catch (ex: IOException) {
                onTheAirTvShows.value = Result.failure(ex)
            }
        }
    }
}