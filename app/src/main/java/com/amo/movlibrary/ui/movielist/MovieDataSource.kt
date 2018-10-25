package com.amo.movlibrary.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.amo.movlibrary.appconstants.AppConstants
import com.amo.movlibrary.data.DataManager
import com.amo.movlibrary.model.Movie
import com.amo.movlibrary.model.MovieResponse
import com.amo.movlibrary.utils.NetworkState
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val type: String,
    private val dataManager: DataManager,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {

    var PAGE = 1

    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    private var retryCompletable: Completable? = null

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ }, {  })
            )
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        val request: Observable<MovieResponse>

        if (type == AppConstants.TV_SERIES)
            request = dataManager.requestTvSeries(PAGE)
        else request = dataManager.requestMovies(type, PAGE)


        compositeDisposable.add(
            request.subscribe(
                { response ->
                    setRetry(null)
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)

                    callback.onResult(response.movies, null, PAGE + 1)
                },
                {
                    setRetry(Action { loadInitial(params, callback) })
                    val error = NetworkState.error(it.message)

                    // publish the error
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                }
            )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        networkState.postValue(NetworkState.LOADING)

        val request: Observable<MovieResponse>

        if (type == AppConstants.TV_SERIES)
            request = dataManager.requestTvSeries(params.key)
        else request = dataManager.requestMovies(type, params.key)

        compositeDisposable.add(
            request.subscribe(
                { response ->
                    setRetry(null)
                    networkState.postValue(NetworkState.LOADED)
                    callback.onResult(response.movies, params.key + 1)
                },
                { throwable ->
                    // keep a Completable for future retry
                    setRetry(Action { loadAfter(params, callback) })
                    // publish the error
                    networkState.postValue(NetworkState.error(throwable.message))
                }
            )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }
}