package com.amo.movlibrary.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setError(res: Int) {
    this.error = context.getString(res)
}

fun Context.showToast(msg: String?) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(msg: String?) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}


fun ViewGroup.getLayoutInflater(): LayoutInflater {
    return LayoutInflater.from(context)
}

fun Fragment.getColorRes(res: Int) = this.resources.getColor(res)