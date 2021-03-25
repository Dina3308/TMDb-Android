package ru.kpfu.itis.tmdb.presentation.rv.backdrop

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.kpfu.itis.tmdb.data.api.response.Results

class BackDropAdapter(
    private var list: List<Results>,
    private val itemClick: (Results) -> Unit
) : RecyclerView.Adapter<BackdropHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackdropHolder =
        BackdropHolder.create(parent, itemClick)

    override fun onBindViewHolder(holder: BackdropHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
}