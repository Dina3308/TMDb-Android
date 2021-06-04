package ru.kpfu.itis.tmdb.presentation.details.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.data.api.response.DetailsResponse
import ru.kpfu.itis.tmdb.presentation.details.BaseViewModel
import java.io.IOException
import javax.inject.Inject

class DetailsMovieViewModel @Inject constructor(
   private val tmdbService: TmdbService
) : ViewModel() {

    private val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val details: MutableLiveData<Result<DetailsResponse>> = MutableLiveData()

    fun progress(): LiveData<Boolean> = progress
    fun details(): MutableLiveData<Result<DetailsResponse>> = details

    fun showDetails(id: Int){
        viewModelScope.launch () {
            try {
                progress.value = true
                details.value = Result.success(tmdbService.getDetailsMovie(id))
            } catch (ex: IOException) {
                details.value = Result.failure(ex)
            }finally {
                progress.value = false
            }
        }
    }

}