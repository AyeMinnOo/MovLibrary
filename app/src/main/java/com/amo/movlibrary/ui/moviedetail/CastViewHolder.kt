package com.amo.movlibrary.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amo.movlibrary.R
import com.amo.movlibrary.model.Cast
import com.amo.movlibrary.utils.setPersonImg
import com.amo.movlibrary.utils.setPosterImage

class CastViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val name: TextView
    private val profile: ImageView

    init {
        name = view.findViewById(R.id.tvName)
        profile = view.findViewById(R.id.imgProfile)
    }

    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): CastViewHolder {
            val view = inflater.inflate(R.layout.item_cast, parent, false)
            return CastViewHolder(view)
        }
    }

    fun bind(cast: Cast) {
        name.text = cast.name
        profile.setPersonImg(cast.profilePath)
    }

}