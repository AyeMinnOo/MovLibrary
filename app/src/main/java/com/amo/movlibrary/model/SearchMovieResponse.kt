package com.amo.movlibrary.model

import com.google.gson.annotations.SerializedName

data class SearchMovie
constructor(
    val id: Long,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("original_title") val title: String,
    @SerializedName("release_date") val releaseDate: String
)

data class SearchMovieResponse
constructor(val results: List<SearchMovie>)