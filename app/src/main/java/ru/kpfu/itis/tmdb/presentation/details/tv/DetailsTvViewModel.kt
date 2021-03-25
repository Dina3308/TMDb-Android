package ru.kpfu.itis.tmdb.presentation.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.data.api.response.DetailsResponse
import java.io.IOException

class DetailsTvViewModel(
    private val tmdbService: TmdbService
) : ViewModel() {

    private val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val detailsTv: MutableLiveData<Result<DetailsResponse>> = MutableLiveData()

    fun progress(): LiveData<Boolean> = progress
    fun detailsTv(): MutableLiveData<Result<DetailsResponse>> = detailsTv

    fun showDetailsTv(id: Int){
        viewModelScope.launch () {
            try {
                progress.value = true
                detailsTv.value = Result.success(tmdbService.getDetailsTv(id))
            } catch (ex: IOException) {
                detailsTv.value = Result.failure(ex)
            }finally {
                progress.value = false
            }
        }
    }

}