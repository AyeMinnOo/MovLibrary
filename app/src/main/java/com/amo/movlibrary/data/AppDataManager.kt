package com.amo.movlibrary.data

import com.amo.movlibrary.data.pref.PrefHelper
import com.amo.movlibrary.data.remote.ApiHelper
import com.amo.movlibrary.model.*
import com.amo.movlibrary.utils.NetworkUtilHelper
import com.amo.movlibrary.utils.RxTransformer
import io.reactivex.Observable
import javax.inject.Inject

class AppDataManager @Inject constructor(
    private val prefHelper: PrefHelper,
    private val apiHelper: ApiHelper,
    private val networkUtilHelper: NetworkUtilHelper
) : DataManager {

    override fun isNetworkNotConnected(): Boolean {
        return networkUtilHelper.isNetworkNotConnected()
    }

    // remote
    override fun requestSessionId(): Observable<AuthenticationResponse> {
        return apiHelper.requestSessionId().compose(RxTransformer.applyIO())
    }

    override fun requestMovies(type: String, page: Int): Observable<MovieResponse> {
        return apiHelper.requestMovies(type, page)
    }

    override fun requestTvSeries(page: Int): Observable<MovieResponse> {
        return apiHelper.requestTvSeries(page)
    }

    override fun getMovieDetail(movieId: Long): Observable<MovieDetailResponse> {
        return apiHelper.getMovieDetail(movieId).compose(RxTransformer.applyIO())
    }

    override fun getSimilarMovies(movieId: Long, page: Int): Observable<MovieResponse> {
        return apiHelper.getSimilarMovies(movieId, page)
    }

    override fun getCasts(movieId: Long): Observable<Casts> {
        return apiHelper.getCasts(movieId).compose(RxTransformer.applyIO())
    }

    override fun searchMovie(query: String): Observable<SearchMovieResponse> {
        return apiHelper.searchMovie(query).compose(RxTransformer.applyIO())
    }

    // preference
    override fun setToken(token: String) {
        prefHelper.setToken(token)
    }

    override fun getToken(): String? {
        return prefHelper.getToken()
    }

    override fun setIsLoggedIn(isLogin: Boolean) {
        prefHelper.setIsLoggedIn(isLogin)
    }

    override fun isLoggedIn(): Boolean {
        return prefHelper.isLoggedIn()
    }

}