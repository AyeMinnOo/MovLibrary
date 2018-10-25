package com.amo.movlibrary.model

import com.google.gson.annotations.SerializedName

data class Movie
constructor(
    @SerializedName("vote_count") val voteAccount: Long, val id: Long, val video: Boolean,
    @SerializedName("vote_average") val voteAverage: String, val title: String, val popularity: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("backdrop_path") val backDropPath: String?,
    val adult: Boolean, val overview: String, @SerializedName("release_date") val releaseDate: String
)

data class MovieResponse
constructor(
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("total_pages") val totalPages: Int
)
