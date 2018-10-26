package com.amo.movlibrary.ui.moviedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amo.movlibrary.R
import com.amo.movlibrary.databinding.ActivityMainBinding
import com.amo.movlibrary.model.Casts
import com.amo.movlibrary.model.MovieDetailResponse
import com.amo.movlibrary.model.MovieResponse
import com.amo.movlibrary.ui.base.BaseActivity
import com.amo.movlibrary.ui.movielist.MovieViewHolder
import com.amo.movlibrary.utils.setBackDropImage
import com.amo.movlibrary.utils.setPosterImage
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.content_movie_detail.*
import kotlinx.android.synthetic.main.header_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : BaseActivity<ActivityMainBinding, MovieDetailViewModel>(),
    MovieDetailNavigator, MovieViewHolder.OnMovieClickListener {

    companion object {

        val LOGO_TAG = "LOGO_TAG"
        val COVER_TAG = "COVER_TAG"
        val ID_TAG = "ID_TAG"

        fun startIntent(context: Context, movieId: Long, logo: String, cover: String): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(LOGO_TAG, logo)
            intent.putExtra(COVER_TAG, cover)
            intent.putExtra(ID_TAG, movieId)
            return intent
        }
    }

    @Inject
    lateinit var vm: MovieDetailViewModel

    override val layoutId: Int
        get() = R.layout.activity_movie_detail

    override val viewModel: MovieDetailViewModel
        get() = vm

    override val bindingVariable: Int
        get() = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        vm.navigator = this
        vm.getDetail(intent?.getLongExtra(ID_TAG, -1)!!)
        initViews()
    }

    private fun initViews() {
        imgCover.setBackDropImage(intent?.getStringExtra(COVER_TAG) ?: "")
        imgProfile.setPosterImage(intent?.getStringExtra(LOGO_TAG) ?: "")

        appbarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(layout: AppBarLayout?, offset: Int) {
                if (Math.abs(offset) == layout?.totalScrollRange) {
                    showTitle(true)
                } else {
                    showTitle(false)
                }
            }
        })
    }

    override fun bind(response: MovieDetailResponse) {
        supportActionBar?.title = response.title
        movieTitle.text = response.title
        releaseDate.text = response.mReleaseDate
        playTime.text = response.mRunTime
        overview.text = response.overview
    }

    override fun bindSimilarMovies(response: MovieResponse) {
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = MovieAdapter(this, this)
        similarRecyclerView.layoutManager = layoutManager
        similarRecyclerView.adapter = adapter
        adapter.bindData(response.movies)
        if (response.movies.isEmpty())
            layout_similar.visibility = View.GONE
    }

    override fun bindCast(cast: Casts) {
        val layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter = CastAdapter()
        castRecyclerView.layoutManager = layoutManager
        castRecyclerView.adapter = adapter
        adapter.bindData(cast.cast)
        if (cast.cast.isEmpty())
            layout_cast.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showTitle(flag: Boolean) {
        supportActionBar?.setDisplayShowTitleEnabled(flag)
    }

    override fun onMovieClick(view: ImageView, id: Long, logo: String?, cover: String?) {
        val intent = MovieDetailActivity.startIntent(this, id, logo ?: "", cover ?: "")
        startActivity(intent)
    }

    override fun networkError() {

    }
}