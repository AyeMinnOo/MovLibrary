package com.amo.movlibrary.data.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.amo.movlibrary.di.PrefInfo
import javax.inject.Inject


class AppPrefHelper @Inject
constructor(context: Context, @PrefInfo prefName: String) : PrefHelper {

    private val pref: SharedPreferences

    init {
        pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    companion object {
        private val PREF_TOKEN = "PREF_TOKEN"
        private val PREF_IS_LOGIN = "PREF_IS_LOGIN"
    }

    override fun setToken(token: String) {
        pref.edit { putString(PREF_TOKEN, token) }
    }

    override fun getToken(): String? {
        return pref.getString(PREF_TOKEN, "")
    }

    override fun setIsLoggedIn(isLogin: Boolean) {
        pref.edit { putBoolean(PREF_IS_LOGIN, isLogin) }
    }

    override fun isLoggedIn(): Boolean {
        return pref.getBoolean(PREF_IS_LOGIN, false)
    }

}