package ru.kpfu.itis.tmdb.presentation.details.tv

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

class DetailsTvFragment  : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    @Inject
    lateinit var viewModel: DetailsTvViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as App).appComponent.detailsTvComponentFactory()
            .create(this)
            .inject(this)

        arguments?.let { DetailsTvFragmentArgs.fromBundle(it).itemId }?.let { viewModel.showDetails(it) }
        initSubscribes()
    }

    private fun initSubscribes() {
        with(viewModel) {
            progress().observe(viewLifecycleOwner, {
                binding.progressBar.isVisible = it
            })
            details().observe(viewLifecycleOwner, {
                try {
                    binding.details = it.getOrThrow()
                    binding.genres = it.getOrThrow().genres.joinToString { results ->
                        results.name
                    }
                } catch (ex: IOException) {
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