package com.amo.movlibrary.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amo.movlibrary.model.SearchMovie

class SearchMovieAdapter
    (val listener: SearchMovieHolder.OnSearchMovieClickListener) : RecyclerView.Adapter<SearchMovieHolder>() {

    val data: MutableList<SearchMovie>

    init {
        data = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchMovieHolder.create(inflater, parent, listener)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: SearchMovieHolder, position: Int) {
        holder.bind(data[position])
    }

    fun bindData(data: List<SearchMovie>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

}