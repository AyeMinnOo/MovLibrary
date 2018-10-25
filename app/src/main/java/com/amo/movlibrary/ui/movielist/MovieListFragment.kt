package com.amo.movlibrary.ui.movielist

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.amo.movlibrary.BR
import com.amo.movlibrary.R
import com.amo.movlibrary.appconstants.AppConstants
import com.amo.movlibrary.databinding.FragmentMovieListBinding
import com.amo.movlibrary.model.Movie
import com.amo.movlibrary.ui.base.BaseFragment
import com.amo.movlibrary.ui.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.fragment_movie_list.*
import javax.inject.Inject

class MovieListFragment : BaseFragment<FragmentMovieListBinding, MovieListViewModel>(), MovieListNavigator,
    MovieViewHolder.OnMovieClickListener {

    companion object {

        val TYPE_TAG = "MOVIE_TYPE"

        fun newInstance(showType: String): Fragment {
            val fragment = MovieListFragment()
            val bundle = Bundle()
            bundle.putString(TYPE_TAG, showType)
            fragment.arguments = bundle
            return fragment
        }

        fun showUpcoming() = newInstance(AppConstants.UPCOMING)
        fun showNowPlaying() = newInstance(AppConstants.NOW_PLAYING)
        fun showPopular() = newInstance(AppConstants.POPULARY)
        fun showTopRated() = newInstance(AppConstants.TOP_RATED)
        fun showTvSeries() = newInstance(AppConstants.TV_SERIES)

    }

    private lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var vm: MovieListViewModel

    override val layoutId: Int
        get() = R.layout.fragment_movie_list

    override val viewModel: MovieListViewModel
        get() = vm

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.navigator = this
        initAdapter()
    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        movieAdapter = MovieAdapter({ vm.retry() }, this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = movieAdapter
        vm.startRequest(arguments?.getString(TYPE_TAG)!!)
        vm.movieList?.observe(this, Observer<PagedList<Movie>> {
            movieAdapter.submitList(it)
            vm.hideLoading()
        })
    }

    override fun onMovieClick(view: ImageView, id: Long, logo: String?, cover: String?) {
        val intent = MovieDetailActivity.startIntent(activity!!, id, logo ?: "", cover ?: "")
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity!!, view, "profile")
        startActivity(intent, options.toBundle())
    }

    override fun networkError() {

    }
}