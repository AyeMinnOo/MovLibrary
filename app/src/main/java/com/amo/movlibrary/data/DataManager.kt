package com.amo.movlibrary.data

import com.amo.movlibrary.data.pref.PrefHelper
import com.amo.movlibrary.data.remote.ApiHelper
import com.amo.movlibrary.utils.NetworkUtil

interface DataManager : ApiHelper, PrefHelper , NetworkUtil {


}