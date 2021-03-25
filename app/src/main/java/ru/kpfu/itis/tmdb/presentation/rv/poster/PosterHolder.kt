package ru.kpfu.itis.tmdb.presentation.rv.poster

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.tmdb.R
import ru.kpfu.itis.tmdb.data.api.response.Results
import ru.kpfu.itis.tmdb.databinding.ItemMovieSmallBinding

class PosterHolder(
    private val binding: ItemMovieSmallBinding,
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
        fun create(parent: ViewGroup, itemClick: (Results) -> Unit): PosterHolder =
            PosterHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie_small, parent, false),
                itemClick
            )

    }

}