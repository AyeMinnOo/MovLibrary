package com.amo.movlibrary.ui.test

import com.amo.movlibrary.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class TestModule {

    @Provides
    fun bind(dataManager: DataManager) = TestViewModel(dataManager)

}