package ru.kpfu.itis.tmdb.presentation.details.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.data.api.response.DetailsResponse
import ru.kpfu.itis.tmdb.presentation.details.BaseViewModel
import java.io.IOException

open class DetailsTvViewModel(
    private val tmdbService: TmdbService
) : BaseViewModel() {

    private val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val details: MutableLiveData<Result<DetailsResponse>> = MutableLiveData()

    override fun progress(): LiveData<Boolean> = progress
    override fun details(): MutableLiveData<Result<DetailsResponse>> = details

    override fun showDetails(id: Int){
        viewModelScope.launch () {
            try {
                progress.value = true
                details.value = Result.success(tmdbService.getDetailsTv(id))
            } catch (ex: IOException) {
                details.value = Result.failure(ex)
            }finally {
                progress.value = false
            }
        }
    }

}