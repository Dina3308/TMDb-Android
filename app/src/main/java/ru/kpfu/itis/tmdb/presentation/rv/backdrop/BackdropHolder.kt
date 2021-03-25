package ru.kpfu.itis.tmdb.presentation.rv.backdrop

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.tmdb.R
import ru.kpfu.itis.tmdb.data.api.response.Results
import ru.kpfu.itis.tmdb.databinding.ItemMovieLargeBinding

class BackdropHolder(
    private val binding: ItemMovieLargeBinding,
    private val itemClick: (Results) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var movie: Results? = null

    init {
        itemView.setOnClickListener {
            movie.also {
                movie?.also(itemClick)
            }
        }
    }

    fun bind(movie: Results) {
        this.movie = movie
        binding.movie = movie
    }

    companion object {
        fun create(parent: ViewGroup, itemClick: (Results) -> Unit): BackdropHolder =
            BackdropHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie_large, parent, false),
                itemClick
            )
    }

}