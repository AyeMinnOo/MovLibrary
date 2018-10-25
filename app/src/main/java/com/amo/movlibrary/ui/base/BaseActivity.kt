package com.amo.movlibrary.ui.base

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.amo.movlibrary.R
import com.tapadoo.alerter.Alerter
import dagger.android.AndroidInjection


abstract class BaseActivity<V : ViewDataBinding, M : BaseViewModel<*>> : AppCompatActivity() {

    var mViewDataBinding: V? = null

    var mViewModel: M? = null

    @get:LayoutRes
    abstract val layoutId: Int

    abstract val viewModel: M

    abstract val bindingVariable: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        performInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    override fun onDestroy() {
        mViewModel?.onCleared()
        super.onDestroy()
    }

    private fun performInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        mViewModel = if (mViewModel == null) viewModel else mViewModel
        mViewDataBinding!!.setVariable(bindingVariable, mViewModel)
        mViewDataBinding!!.executePendingBindings()
    }

    protected fun showNetworkError(activity: Activity) {
        Alerter.create(activity)
            .setIcon(R.drawable.ic_network_check)
            .setBackgroundColorRes(R.color.colorPrimaryDark)
            .setText(R.string.alert_network)
            .setDuration(1000)
            .show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}