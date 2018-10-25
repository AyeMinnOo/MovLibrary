package com.amo.movlibrary.ui.test

import com.amo.movlibrary.data.DataManager
import com.amo.movlibrary.ui.base.BaseViewModel

class TestViewModel(dataManager: DataManager) : BaseViewModel<TestNavigator>(dataManager) {

    override fun alertNetworkError() {
//
    }

    override fun showLoading() {
//
    }

    override fun hideLoading() {
//
    }
}