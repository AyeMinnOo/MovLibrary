package com.amo.movlibrary.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.amo.movlibrary.data.DataManager
import com.amo.movlibrary.model.Movie
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(
    private val type: String,
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val movieDataSourceLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(type, dataManager, compositeDisposable)
        movieDataSourceLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}