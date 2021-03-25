package ru.kpfu.itis.tmdb.presentation.details.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.tmdb.R
import ru.kpfu.itis.tmdb.data.api.ApiFactory
import ru.kpfu.itis.tmdb.databinding.FragmentDetailsBinding
import ru.kpfu.itis.tmdb.presentation.ViewModelFactory
import java.io.IOException

class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    lateinit var viewModel: DetailsMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        viewModel = ViewModelProvider(this, initFactory()).get(DetailsMovieViewModel::class.java)
        arguments?.let { DetailsMovieFragmentArgs.fromBundle(it).itemId }?.let { viewModel.showDetailsMovie(it) }
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
            detailsMovie().observe(viewLifecycleOwner, {
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