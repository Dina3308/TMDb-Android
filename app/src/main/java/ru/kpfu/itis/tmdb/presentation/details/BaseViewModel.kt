package ru.kpfu.itis.tmdb.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kpfu.itis.tmdb.data.api.response.DetailsResponse

abstract class BaseViewModel : ViewModel() {

    abstract fun progress(): LiveData<Boolean>

    abstract fun details(): MutableLiveData<Result<DetailsResponse>>

    abstract fun showDetails(id: Int)
}