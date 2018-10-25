package com.amo.movlibrary.di

import com.amo.movlibrary.ui.main.MainActivity
import com.amo.movlibrary.ui.main.MainActivityModule
import com.amo.movlibrary.ui.moviedetail.MovieDetailActivity
import com.amo.movlibrary.ui.moviedetail.MovieDetailModule
import com.amo.movlibrary.ui.movielist.MovieListModuleProvider
import com.amo.movlibrary.ui.test.TestActivity
import com.amo.movlibrary.ui.test.TestModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, MovieListModuleProvider::class])
    abstract fun bindMain(): MainActivity

    @ContributesAndroidInjector(modules = [MovieDetailModule::class])
    abstract fun bindMovieDetail(): MovieDetailActivity

    @ContributesAndroidInjector(modules = [TestModule::class])
    abstract fun bindTest(): TestActivity

}