package ru.kpfu.itis.tmdb.presentation.tv_shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.tmdb.App
import ru.kpfu.itis.tmdb.R
import ru.kpfu.itis.tmdb.databinding.FragmentMoviesBinding
import ru.kpfu.itis.tmdb.presentation.rv.SpaceItemDecoration
import ru.kpfu.itis.tmdb.presentation.rv.backdrop.BackDropAdapter
import ru.kpfu.itis.tmdb.presentation.rv.poster.PosterAdapter
import javax.inject.Inject

class TvShowsFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    @Inject
    lateinit var viewModel: TvShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as App).appComponent.tvShowsComponentFactory()
            .create(this)
            .inject(this)

        with(binding){
            titleToolbar = "Tv Shows"
            upComing = "On The Air"
            nowPlaying = "Airing Today"
        }

        initSubscribes()
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

    private fun navigateToDetailsFragment(id: Int){
        TvShowsFragmentDirections.actionTvShowsFragmentToDetailsTvFragment(id).also {
            findNavController().navigate(it.actionId, it.arguments)
        }
    }
}