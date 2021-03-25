package ru.kpfu.itis.tmdb.presentation.tv_shows

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.tmdb.R
import ru.kpfu.itis.tmdb.data.api.ApiFactory
import ru.kpfu.itis.tmdb.databinding.FragmentMoviesBinding
import ru.kpfu.itis.tmdb.presentation.ViewModelFactory
import ru.kpfu.itis.tmdb.presentation.movies.MoviesFragmentDirections
import ru.kpfu.itis.tmdb.presentation.rv.SpaceItemDecoration
import ru.kpfu.itis.tmdb.presentation.rv.backdrop.BackDropAdapter
import ru.kpfu.itis.tmdb.presentation.rv.poster.PosterAdapter

class TvShowsFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    lateinit var viewModel: TvShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        viewModel = ViewModelProvider(this, initFactory()).get(TvShowsViewModel::class.java)

        with(binding){
            titleToolbar = "Tv Shows"
            upComing = "On The Air"
            nowPlaying = "Airing Today"
        }

        initSubscribes()
        return binding.root
    }

    private fun initSubscribes(){
        with(viewModel){
            progress().observe(viewLifecycleOwner, {
                binding.progressBar.isVisible = it
            })
            popularTvShows().observe(viewLifecycleOwner, {results ->
                results.getOrNull()?.apply {
                    binding.popularMoviesRv.adapter = PosterAdapter(this){
                        navigateToDetailsFragment(it.id)
                    }
                    binding.popularMoviesRv.addItemDecoration(SpaceItemDecoration(activity))
                }
            })
            airingTodayTvShows().observe(viewLifecycleOwner, {results ->
                results.getOrNull()?.apply {
                    binding.nowPlayingMoviesRv.adapter = BackDropAdapter(this){
                        navigateToDetailsFragment(it.id)
                    }
                    binding.nowPlayingMoviesRv.addItemDecoration(SpaceItemDecoration(activity))
                }
            })
            topRatedTvShows().observe(viewLifecycleOwner, {results ->
                results.getOrNull()?.apply {
                    binding.topRatedRv.adapter = PosterAdapter(this){
                        navigateToDetailsFragment(it.id)
                    }
                    binding.topRatedRv.addItemDecoration(SpaceItemDecoration(activity))
                }
            })
            onTheAirTvShows().observe(viewLifecycleOwner, {results ->
                try{
                    binding.upcomingRv.adapter = BackDropAdapter(results.getOrThrow()){
                        navigateToDetailsFragment(it.id)
                    }
                    binding.upcomingRv.addItemDecoration(SpaceItemDecoration(activity))
                } catch (throwable: Throwable){
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        "no connection",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            })
        }
    }

    private fun initFactory() = ViewModelFactory(
        ApiFactory.tmdbService
    )

    private fun navigateToDetailsFragment(id: Int){
        MoviesFragmentDirections.movieDetails(id).also {
            findNavController().navigate(it.actionId, it.arguments)
        }
    }
}