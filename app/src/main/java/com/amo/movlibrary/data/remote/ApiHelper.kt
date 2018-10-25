package com.amo.movlibrary.data.remote

import com.amo.movlibrary.model.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiHelper {

    @GET("authentication/guest_session/new")
    fun requestSessionId(): Observable<AuthenticationResponse>

    @GET("movie/{type}")
    fun requestMovies(@Path("type") type: String, @Query("page") page: Int): Observable<MovieResponse>

    @GET("tv/popular")
    fun requestTvSeries(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Long): Observable<MovieDetailResponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movieId: Long, @Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/{movie_id}/casts")
    fun getCasts(@Path("movie_id") movieId: Long): Observable<Casts>

    // search/movie?api_key=69cf005df3c51347885752484b8e05e2&language=en-US&query=gigolos&page=1&include_adult=true
    @GET("search/movie")
    fun searchMovie(@Query("query") query: String) : Observable<SearchMovieResponse>

}