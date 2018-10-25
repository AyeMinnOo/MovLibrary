package com.amo.movlibrary.utils

import android.content.Context
import android.util.DisplayMetrics


class MetricsUtil {

    companion object {
        fun dpToPx(dp: Int, context: Context): Int {
            val displayMetrics = context.getResources().getDisplayMetrics()
            return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

        fun pxToDp(px: Int, context: Context): Int {
            val displayMetrics = context.getResources().getDisplayMetrics()
            return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

    }

}