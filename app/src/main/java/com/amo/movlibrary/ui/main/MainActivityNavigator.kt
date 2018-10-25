package com.amo.movlibrary.ui.main

import com.amo.movlibrary.model.SearchMovieResponse
import com.amo.movlibrary.ui.base.NetworkErrorNavigator

interface MainActivityNavigator : NetworkErrorNavigator {

    fun bind(response : SearchMovieResponse)

}