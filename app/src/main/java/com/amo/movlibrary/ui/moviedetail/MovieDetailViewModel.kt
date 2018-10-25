package com.amo.movlibrary.ui.moviedetail

import com.amo.movlibrary.data.DataManager
import com.amo.movlibrary.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel(dataManger: DataManager) : BaseViewModel<MovieDetailNavigator>(dataManger) {

    override fun alertNetworkError() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    fun getDetail(movieId: Long) {
        compositeDisposable.add(
            dataManager.getMovieDetail(movieId)
                .subscribe(
                    { response ->
                        navigator?.bind(response)
                    },
                    {

                    }
                )
        )

        getSimilarMovies(movieId)
        getCast(movieId)
    }

    fun getSimilarMovies(movieId: Long) {
        compositeDisposable.add(
            dataManager.getSimilarMovies(movieId, 1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        navigator?.bindSimilarMovies(response)
                    },
                    {

                    }
                )
        )
    }

    fun getCast(movieId: Long) {
        compositeDisposable.add(
            dataManager.getCasts(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { response ->
                        navigator?.bindCast(response)
                    },
                    {

                    }
                )
        )
    }


}