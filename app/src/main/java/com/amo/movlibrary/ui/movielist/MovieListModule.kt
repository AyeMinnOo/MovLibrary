package com.amo.movlibrary.ui.movielist

import com.amo.movlibrary.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class MovieListModule {

    @Provides
    fun provide(dataManger: DataManager) = MovieListViewModel(dataManger)

}