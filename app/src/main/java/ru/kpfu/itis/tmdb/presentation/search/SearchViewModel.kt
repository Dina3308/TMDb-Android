package ru.kpfu.itis.tmdb.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kpfu.itis.tmdb.data.api.TmdbService
import ru.kpfu.itis.tmdb.data.api.response.Results
import java.io.IOException

class SearchViewModel(
    private val tmdbService: TmdbService
) : ViewModel(){

    private val progress: MutableLiveData<Boolean> = MutableLiveData()
    private val multiSearch: MutableLiveData<Result<List<Results>>> = MutableLiveData()

    fun progress(): LiveData<Boolean> = progress

    fun multiSearch(): MutableLiveData<Result<List<Results>>> = multiSearch

    fun searchTvOrMovie(searchQuery: String){
        viewModelScope.launch{
            try{
                progress.value = true
                multiSearch.value = Result.success(tmdbService.getMultiSearch(searchQuery).results)
            }
            catch (ex: IOException){
                multiSearch.value = Result.failure(ex)
            }
            finally {
                progress.value = false
            }
        }
    }

}
