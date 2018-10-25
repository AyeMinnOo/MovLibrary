package com.amo.movlibrary.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.amo.movlibrary.R
import com.amo.movlibrary.databinding.ItemMovieBinding
import com.amo.movlibrary.model.Movie

class MovieViewHolder(
    val binding: ItemMovieBinding,
    val listener: OnMovieClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface OnMovieClickListener {
        fun onMovieClick(view: ImageView, id: Long, logo: String?, cover: String?)
    }

    init {
        itemView.setOnClickListener {
            val movie = binding.movie!!
            listener.onMovieClick(
                itemView.findViewById(R.id.imgProfile),
                movie.id,
                movie.posterPath,
                movie.backDropPath
            )
        }
    }

    companion object {
        fun create(inflater: LayoutInflater, viewGroup: ViewGroup, listener: OnMovieClickListener): MovieViewHolder {
            val binding = ItemMovieBinding.inflate(inflater, viewGroup, false)
            return MovieViewHolder(binding, listener)
        }
    }

    fun bind(movie: Movie) {
        binding.movie = movie
        binding.executePendingBindings()
    }

}