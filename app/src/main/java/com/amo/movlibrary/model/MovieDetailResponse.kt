package com.amo.movlibrary.model

import com.google.gson.annotations.SerializedName

data class Genres(val name: String)

data class MovieDetailResponse
constructor(
    @SerializedName("original_title") val title: String,
    val genres: List<Genres>, val homepage: String, val overview: String,
    val budget: String, @SerializedName("release_date") val releaseDate: String,
    val revenue: String, val runtime: String
) {

    val mGeners: String
        get() {
            var result = genres[0].name
            for (i in 1..result.length)
                result += ", ${genres[i].name}"
            return result
        }

    val mBudget: String
        get() = "Budget- $budget $"

    val mRevenue: String
        get() = "Revenue- $revenue $"

    val mReleaseDate : String
        get() = "Released- $releaseDate"

    val mRunTime: String
        get() = "Play Time- $runtime min"

}