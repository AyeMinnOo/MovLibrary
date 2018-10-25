package com.amo.movlibrary.model

import com.google.gson.annotations.SerializedName

data class Cast
constructor(
    @SerializedName("cast_id") val caseId: Int, val character: String,
    @SerializedName("credit_id") val creditId: String, val name: String,
    @SerializedName("profile_path") val profilePath: String?
)

data class Casts(val cast: List<Cast>)