package com.amo.movlibrary.ui.movielist

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieListModuleProvider {

    @ContributesAndroidInjector(modules = [MovieListModule::class])
    abstract fun bind(): MovieListFragment

}