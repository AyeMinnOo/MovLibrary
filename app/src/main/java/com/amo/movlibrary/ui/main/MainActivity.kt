package com.amo.movlibrary.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amo.movlibrary.BR
import com.amo.movlibrary.R
import com.amo.movlibrary.appconstants.AppConstants
import com.amo.movlibrary.databinding.ActivityMainBinding
import com.amo.movlibrary.model.SearchMovieResponse
import com.amo.movlibrary.ui.base.BaseActivity
import com.amo.movlibrary.ui.moviedetail.MovieDetailActivity
import com.amo.movlibrary.ui.movielist.MovieListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), HasSupportFragmentInjector,
    MainActivityNavigator, SearchMovieHolder.OnSearchMovieClickListener {

    @Inject
    lateinit var vm: MainViewModel
    private var adapter: SearchMovieAdapter? = null
    private var searchView: SearchView? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: MainViewModel
        get() = vm

    override val bindingVariable: Int
        get() = BR.viewModel

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_upcoming -> {
                supportActionBar?.setTitle(R.string.navi_upcoming)
                openFragment(AppConstants.UPCOMING)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_popular -> {
                supportActionBar?.setTitle(R.string.navi_popular)
                openFragment(AppConstants.POPULARY)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_top -> {
                supportActionBar?.setTitle(R.string.navi_top_rated)
                openFragment(AppConstants.TOP_RATED)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_playing -> {
                supportActionBar?.setTitle(R.string.navi_playing)
                openFragment(AppConstants.NOW_PLAYING)
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_serie -> {
                supportActionBar?.setTitle(R.string.navi_tvserie)
                openFragment(AppConstants.TV_SERIES)
                return@OnNavigationItemSelectedListener true
            }
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.navigator = this
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        supportActionBar?.setTitle(R.string.navi_upcoming)
        openFragment(AppConstants.UPCOMING)

        adapter = SearchMovieAdapter(this)
        searchRecyclerView.adapter = adapter
        searchRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = searchItem?.actionView!! as SearchView
        searchView?.isIconified = true
        searchView?.queryHint = resources.getString(R.string.hint_search_movie)
        searchView?.maxWidth = Integer.MAX_VALUE
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                vm.searchMovie(newText!!)
                return true
            }
        })

        searchView?.setOnSearchClickListener {
            Log.e("Main", "click")
            layout_search.visibility = View.VISIBLE
        }

        searchView?.setOnCloseListener {
            Log.e("Main", "close")
            layout_search.visibility = View.INVISIBLE
            false
        }

        return true
    }

    private fun openFragment(type: String) {
        if (supportFragmentManager.findFragmentByTag(type) != null) return
        val fragment: Fragment
        val transaction = supportFragmentManager.beginTransaction()
        when (type) {
            AppConstants.UPCOMING -> fragment = MovieListFragment.showUpcoming()
            AppConstants.POPULARY -> fragment = MovieListFragment.showPopular()
            AppConstants.TOP_RATED -> fragment = MovieListFragment.showTopRated()
            AppConstants.NOW_PLAYING -> fragment = MovieListFragment.showNowPlaying()
            AppConstants.TV_SERIES -> fragment = MovieListFragment.showTvSeries()
            else ->
                fragment = MovieListFragment.showUpcoming()
        }
        transaction.replace(R.id.container, fragment, type)
//        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun closeSearchView() {
        if (!searchView!!.isIconified())
            searchView!!.setIconified(true);

    }

    override fun bind(response: SearchMovieResponse) {
        adapter?.bindData(response.results)
    }

    override fun onSearchMovieClick(movieId: Long, logo: String?, cover: String?) {
        startActivity(MovieDetailActivity.startIntent(this, movieId, logo ?: "", cover ?: ""))
    }

    override fun networkError() {

    }
}