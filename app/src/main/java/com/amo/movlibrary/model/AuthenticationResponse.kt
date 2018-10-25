package com.amo.movlibrary.model

import com.google.gson.annotations.SerializedName

data class AuthenticationResponse
constructor(
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("guest_session_id") val token: String,
    @SerializedName("status_code") val statusCode: Int?,
    @SerializedName("status_message") val statusMsg: String?
)