package com.amo.movlibrary.ui.moviedetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amo.movlibrary.R
import com.amo.movlibrary.model.Movie
import com.amo.movlibrary.ui.movielist.MovieViewHolder

class MovieAdapter(
    private val context: Context,
    private val listener: MovieViewHolder.OnMovieClickListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    private val data: MutableList<Movie>

    init {
        data = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder.create(inflater, parent, listener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val params = holder.itemView.layoutParams
        params.width = context.resources.getDimensionPixelOffset(R.dimen.item_movie_width)
        holder.bind(data[position])
    }

    fun bindData(movies: List<Movie>) {
        data.addAll(movies)
        notifyDataSetChanged()
    }

}