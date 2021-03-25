package ru.kpfu.itis.tmdb.presentation.rv.search

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.kpfu.itis.tmdb.data.api.response.Results
import ru.kpfu.itis.tmdb.presentation.rv.poster.PosterHolder

class SearchAdapter(
    private val list: List<Results>,
    private val itemClick: (Results) -> Unit
) : ListAdapter<Results, PosterHolder>(object : DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean =
        oldItem == newItem
}){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterHolder =
        PosterHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: PosterHolder, position: Int) =
        holder.bind(list[position])
}