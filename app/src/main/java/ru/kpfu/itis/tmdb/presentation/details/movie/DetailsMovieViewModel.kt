package ru.kpfu.itis.tmdb.presentation.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.data.api.response.DetailsResponse
import java.io.IOException

class DetailsMovieViewModel(
   private val tmdbService: TmdbService
) : ViewModel() {

    private val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val detailsMovie: MutableLiveData<Result<DetailsResponse>> = MutableLiveData()

    fun progress(): LiveData<Boolean> = progress
    fun detailsMovie(): MutableLiveData<Result<DetailsResponse>> = detailsMovie

    fun showDetailsMovie(id: Int){
        viewModelScope.launch () {
            try {
                progress.value = true
                detailsMovie.value = Result.success(tmdbService.getDetailsMovie(id))
            } catch (ex: IOException) {
                detailsMovie.value = Result.failure(ex)
            }finally {
                progress.value = false
            }
        }
    }

}