package com.amo.movlibrary.ui.moviedetail

import com.amo.movlibrary.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class MovieDetailModule {

    @Provides
    fun bind(dataManger: DataManager) = MovieDetailViewModel(dataManger)

}