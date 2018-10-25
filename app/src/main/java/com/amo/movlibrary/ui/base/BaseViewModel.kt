package com.amo.movlibrary.ui.base

import androidx.lifecycle.ViewModel
import com.amo.movlibrary.data.DataManager
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N>(val dataManager: DataManager) : ViewModel() {

    private val TAG = javaClass.simpleName

    internal var mNavigator: WeakReference<N>? = null
    internal val compositeDisposable: CompositeDisposable

    init {
        compositeDisposable = CompositeDisposable()
    }

    abstract fun alertNetworkError()

    abstract fun showLoading()
    abstract fun hideLoading()

    var navigator: N?
        get() {
            if (mNavigator!!.get() == null)
                throw NullPointerException("Forgot to set Navigator to $TAG")
            return mNavigator!!.get()
        }
        set(mNavigator) {
            this.mNavigator = WeakReference(mNavigator!!)
        }

    public override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun assertNetworkError(alertNetworkError: () -> Unit): Boolean {
        if (dataManager.isNetworkNotConnected()) {
            alertNetworkError()
            return true
        } else return false
    }

}