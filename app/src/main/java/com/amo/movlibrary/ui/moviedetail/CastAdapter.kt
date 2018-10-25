package com.amo.movlibrary.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amo.movlibrary.model.Cast

class CastAdapter() : RecyclerView.Adapter<CastViewHolder>() {

    private val data: MutableList<Cast>

    init {
        data = mutableListOf()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CastViewHolder.create(inflater, parent)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun bindData(casts: List<Cast>) {
        data.addAll(casts)
        notifyDataSetChanged()
    }

}