package com.amo.movlibrary.ui.main

import com.amo.movlibrary.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun bind(dataManager: DataManager) = MainViewModel(dataManager)

}