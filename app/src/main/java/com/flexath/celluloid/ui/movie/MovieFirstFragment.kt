package com.flexath.celluloid.ui.movie

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.movie.first.FirstNowPlayingAdapter
import com.flexath.celluloid.adapters.movie.first.FirstPopularAdapter
import com.flexath.celluloid.adapters.movie.first.FirstTopRatedAdapter
import com.flexath.celluloid.adapters.movie.first.FirstUpComingAdapter
import com.flexath.celluloid.data.database.movie.Result
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_first.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MovieFirstFragment : Fragment() {

    private var adpaterNowPlaying: FirstNowPlayingAdapter? = null
    private var adpaterUpComing: FirstUpComingAdapter? = null
    private var adpaterPopular: FirstPopularAdapter? = null
    private var adapterTopRated: FirstTopRatedAdapter? = null

    private lateinit var horizontalLinearLayoutNowPlaying:LinearLayoutManager
    private lateinit var horizontalLinearLayoutUpComing:LinearLayoutManager
    private lateinit var horizontalLinearLayoutPopular:LinearLayoutManager
    private lateinit var horizontalLinearLayoutTopRated:LinearLayoutManager

    private lateinit var viewModel:MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_first, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        horizontalLinearLayoutNowPlaying = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutUpComing = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutPopular = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutTopRated = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        getNowPlayingRecyclerSetup()
        getUpComingRecyclerSetup()
        getPopularRecyclerSetup()
        getTopRatedRecyclerSetup()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getNowPlayingRecyclerSetup() {
        rvNowPlaying.layoutManager = horizontalLinearLayoutNowPlaying
        rvNowPlaying.setHasFixedSize(true)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDate.now().format(formatter)

        // api link -> https://api.themoviedb.org/3/discover/movie?api_key=ad1e17e27becb3abedf8eaccc61b8d5d&release_date.gte=2022-10-24&sort_by=release_date.asc&with_original_language=en
        viewModel.getAllNowPlayingMovies(URL.api_key,"release_date.asc",current,"en")
        viewModel.nowPlayingMovieList.observe(viewLifecycleOwner) {
            adpaterNowPlaying = FirstNowPlayingAdapter(it.results as ArrayList<Result>)
            rvNowPlaying.adapter = adpaterNowPlaying
            adpaterNowPlaying?.notifyDataSetChanged()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUpComingRecyclerSetup() {
        rvUpComing.layoutManager = horizontalLinearLayoutUpComing
        rvUpComing.setHasFixedSize(true)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val current = LocalDate.now().format(formatter)

        // api link -> https://api.themoviedb.org/3/discover/movie?api_key=ad1e17e27becb3abedf8eaccc61b8d5d&primary_release_date.gte=2022-10-23&sort_by=release_date.asc&with_original_language=en
        viewModel.getAllUpComingMovies(URL.api_key,"release_date.asc",current,"en")
        viewModel.upComingMovieList.observe(viewLifecycleOwner) {
            adpaterUpComing = FirstUpComingAdapter(it.results as ArrayList<Result>)
            rvUpComing.adapter = adpaterUpComing
            adpaterUpComing?.notifyDataSetChanged()
        }
    }

    private fun getPopularRecyclerSetup() {
        rvPopular.layoutManager = horizontalLinearLayoutPopular
        rvPopular.setHasFixedSize(true)

        // api link -> https://api.themoviedb.org/3/discover/movie?api_key=ad1e17e27becb3abedf8eaccc61b8d5d
        viewModel.getAllPopularMovies(URL.api_key)
        viewModel.popularMovieList.observe(viewLifecycleOwner) {
            adpaterPopular = FirstPopularAdapter(it.results as ArrayList<Result>)
            rvPopular.adapter = adpaterPopular
            adpaterPopular?.notifyDataSetChanged()
        }
    }

    private fun getTopRatedRecyclerSetup() {
        rvTopRated.layoutManager = horizontalLinearLayoutTopRated
        rvTopRated.setHasFixedSize(true)
        // api link -> https://api.themoviedb.org/3/discover/movie?api_key=ad1e17e27becb3abedf8eaccc61b8d5d&vote_count.gte=300&sort_by=vote_average.desc
        viewModel.getAllTopRatedMovies(URL.api_key,300,"vote_average.desc",1)
        viewModel.topRatedMovieList.observe(viewLifecycleOwner) {
            adapterTopRated = FirstTopRatedAdapter(it.results as ArrayList<Result>)
            rvTopRated.adapter = adapterTopRated
            adapterTopRated?.notifyDataSetChanged()
        }
    }
}