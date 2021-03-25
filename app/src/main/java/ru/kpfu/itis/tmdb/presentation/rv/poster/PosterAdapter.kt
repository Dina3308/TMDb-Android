package ru.kpfu.itis.tmdb.presentation.rv.poster

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.tmdb.data.api.response.Results

class PosterAdapter(
    private var list: List<Results>,
    private val itemClick: (Results) -> Unit
) : RecyclerView.Adapter<PosterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterHolder =
        PosterHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: PosterHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}