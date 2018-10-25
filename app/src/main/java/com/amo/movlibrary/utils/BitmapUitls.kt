package com.amo.movlibrary.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

object BitmapUitls {

    fun getBytes(bitmap: Bitmap?): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    fun getBitmap(data: ByteArray?): Bitmap =
            BitmapFactory.decodeByteArray(data, 0, data!!.size)


}