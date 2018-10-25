package com.amo.movlibrary.ui.moviedetail

import com.amo.movlibrary.model.Casts
import com.amo.movlibrary.model.MovieDetailResponse
import com.amo.movlibrary.model.MovieResponse
import com.amo.movlibrary.ui.base.NetworkErrorNavigator

interface MovieDetailNavigator : NetworkErrorNavigator {

    fun bind(response : MovieDetailResponse)
    fun bindSimilarMovies(response: MovieResponse)
    fun bindCast(cast : Casts)

}