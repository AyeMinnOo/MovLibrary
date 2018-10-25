package com.amo.movlibrary.ui.movielist

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.amo.movlibrary.data.DataManager
import com.amo.movlibrary.model.Movie
import com.amo.movlibrary.ui.base.BaseViewModel

class MovieListViewModel(dataManger: DataManager) : BaseViewModel<MovieListNavigator>(dataManger) {

    val loadingView = ObservableInt()

    override fun alertNetworkError() {
    }

    override fun showLoading() {
        loadingView.set(View.VISIBLE)
    }

    override fun hideLoading() {
        loadingView.set(View.INVISIBLE)
    }

    var movieList: LiveData<PagedList<Movie>>? = null

    private var sourceFactory: MovieDataSourceFactory? = null

    fun startRequest(type: String) {
        sourceFactory = MovieDataSourceFactory(type, dataManager, compositeDisposable)
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .build()
        movieList = LivePagedListBuilder(sourceFactory!!, config).build()
    }

    fun retry() {

    }


}