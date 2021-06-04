package ru.kpfu.itis.tmdb.presentation.multi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.tmdb.App
import ru.kpfu.itis.tmdb.R
import ru.kpfu.itis.tmdb.databinding.FragmentDetailsBinding
import java.io.IOException
import javax.inject.Inject

class DetailsMultiFragment : Fragment(){

    private lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var viewModel: DetailsMultiViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as App).appComponent.detailsMultiComponentFactory()
            .create(this)
            .inject(this)

        val arguments = arguments?.let { DetailsMultiFragmentArgs.fromBundle(it) }

        if(arguments?.type == "tv"){
            viewModel.showDetailsTv(arguments.itemId)
        }
        else{
            if (arguments != null) {
                viewModel.showDetailsMovie(arguments.itemId)
            }
        }

        initSubscribes()
    }

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