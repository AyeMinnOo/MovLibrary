package com.amo.movlibrary.utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import javax.inject.Inject

class NetworkUtilHelper @Inject constructor(val context: Context) : NetworkUtil {

    override fun isNetworkNotConnected(): Boolean {
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return !(networkInfo != null && networkInfo.isConnected)
    }
}