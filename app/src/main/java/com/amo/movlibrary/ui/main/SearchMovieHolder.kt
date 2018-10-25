package com.amo.movlibrary.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amo.movlibrary.databinding.ItemSearchMovieBinding
import com.amo.movlibrary.model.SearchMovie

class SearchMovieHolder(
    val binding: ItemSearchMovieBinding,
    val listener: OnSearchMovieClickListener
) : RecyclerView.ViewHolder(binding.root) {

    interface OnSearchMovieClickListener {
        fun onSearchMovieClick(movieId: Long, logo: String?, cover: String?)
    }

    init {
        itemView.setOnClickListener {
            val movie = binding.movie!!
            listener.onSearchMovieClick(movie.id, movie.posterPath, movie.backdropPath)
        }
    }

    companion object {
        fun create(
            inflater: LayoutInflater,
            parent: ViewGroup,
            listener: OnSearchMovieClickListener
        ): SearchMovieHolder {
            val binding = ItemSearchMovieBinding.inflate(inflater, parent, false)
            return SearchMovieHolder(binding, listener)
        }
    }

    fun bind(item: SearchMovie) {
        binding.movie = item
        binding.executePendingBindings()
    }


}