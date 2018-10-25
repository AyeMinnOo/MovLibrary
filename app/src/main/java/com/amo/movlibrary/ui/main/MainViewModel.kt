package com.amo.movlibrary.ui.main

import com.amo.movlibrary.data.DataManager
import com.amo.movlibrary.ui.base.BaseViewModel

class MainViewModel(dataManger: DataManager) : BaseViewModel<MainActivityNavigator>(dataManger) {

    override fun alertNetworkError() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    fun searchMovie(query: String) {
        compositeDisposable.add(
            dataManager.searchMovie(query)
                .subscribe(
                    { response ->
                        navigator?.bind(response)
                    },
                    {

                    }
                )
        )
    }


}