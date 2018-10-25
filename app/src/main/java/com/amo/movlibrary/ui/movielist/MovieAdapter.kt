package com.amo.movlibrary.ui.movielist

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.amo.movlibrary.model.Movie
import com.amo.movlibrary.utils.NetworkState
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    private val retryCallback: () -> Unit, val listener: MovieViewHolder.OnMovieClickListener
) : PagedListAdapter<Movie, MovieViewHolder>(userDiffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder.create(inflater, parent, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        if (Build.VERSION.SDK_INT >= 21)
            holder.itemView.imgProfile.transitionName = "profile"
    }

    companion object {
        val userDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

}