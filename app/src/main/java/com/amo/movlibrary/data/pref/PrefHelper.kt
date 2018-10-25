package com.amo.movlibrary.data.pref

interface PrefHelper {


    fun setToken(token : String)
    fun getToken() : String?

    fun setIsLoggedIn(isLogin : Boolean)
    fun isLoggedIn(): Boolean

}