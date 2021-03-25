package ru.kpfu.itis.tmdb.presentation.multi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.tmdb.R
import ru.kpfu.itis.tmdb.data.api.ApiFactory
import ru.kpfu.itis.tmdb.databinding.FragmentDetailsBinding
import ru.kpfu.itis.tmdb.presentation.ViewModelFactory
import ru.kpfu.itis.tmdb.presentation.details.BaseViewModel
import ru.kpfu.itis.tmdb.presentation.details.movie.DetailsMovieFragmentArgs
import ru.kpfu.itis.tmdb.presentation.details.movie.DetailsMovieViewModel
import ru.kpfu.itis.tmdb.presentation.details.tv.DetailsTvViewModel
import java.io.IOException

class DetailsMultiFragment : Fragment(){

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: BaseViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        val arguments = arguments?.let { DetailsMultiFragmentArgs.fromBundle(it) }

        if(arguments?.type == "tv"){
            viewModel = ViewModelProvider(this, initFactory()).get(DetailsTvViewModel::class.java)
            (viewModel as DetailsTvViewModel).showDetails(arguments.itemId)
        }
        else{
            viewModel = ViewModelProvider(this, initFactory()).get(DetailsMovieViewModel::class.java)
            if (arguments != null) {
                (viewModel as DetailsMovieViewModel).showDetails(arguments.itemId)
            }
        }
        initSubscribes()
        return binding.root
    }

    private fun initFactory() = ViewModelFactory(
            ApiFactory.tmdbService
    )

    private fun initSubscribes(){
        with(viewModel) {
            progress().observe(viewLifecycleOwner, {
                binding.progressBar.isVisible = it
            })
            details().observe(viewLifecycleOwner, {
                try {
                    binding.details = it.getOrThrow()
                    binding.genres =  it.getOrThrow().genres.joinToString {results ->
                        results.name
                    }
                } catch (ex: IOException){
                    Snackbar.make(
                            requireActivity().findViewById(android.R.id.content),
                            "no connection",
                            Snackbar.LENGTH_LONG
                    ).show()
                }
            })
        }
    }
}