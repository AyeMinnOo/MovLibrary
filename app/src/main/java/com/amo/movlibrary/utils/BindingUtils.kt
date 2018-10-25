package com.amo.movlibrary.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.amo.movlibrary.R
import com.amo.movlibrary.appconstants.ApiEndpoint.BACKDROP_PATH
import com.amo.movlibrary.appconstants.ApiEndpoint.POSTER_PATH
import com.squareup.picasso.Picasso


@BindingAdapter("posterImg")
public fun ImageView.setPosterImage(url: String?) {

    if (url == null || url == "") {
        Picasso.get().load(R.drawable.img_movie)
            .resize(256, 256)
            .onlyScaleDown()
            .centerInside()
            .into(this)
    } else
        Picasso.get().load(POSTER_PATH + url)
            .resize(256, 256)
            .onlyScaleDown()
            .centerInside()
            .into(this)
}

@BindingAdapter("personImg")
public fun ImageView.setPersonImg(url: String?) {

    if (url == null || url == "") {
        Picasso.get().load(R.drawable.ic_person)
            .resize(256, 256)
            .onlyScaleDown()
            .centerInside()
            .into(this)
    } else
        Picasso.get().load(POSTER_PATH + url)
            .resize(256, 256)
            .onlyScaleDown()
            .centerInside()
            .into(this)
}

@BindingAdapter("backDropImg")
public fun ImageView.setBackDropImage(url: String?) {
    if (url == null || url == "")
        Picasso.get().load(R.drawable.img_movie)
            .resize(500, 500)
            .onlyScaleDown()
            .centerInside()
            .into(this)
    else Picasso.get().load(BACKDROP_PATH + url)
        .resize(500, 500)
        .onlyScaleDown()
        .centerInside()
        .into(this)
}