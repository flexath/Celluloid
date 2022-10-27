package com.flexath.celluloid.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.movie.first.FirstMovieSearchAdapter
import com.flexath.celluloid.adapters.tv_show.SecondTvShowSearchAdapter
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.database.movie.Result
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_search.*

class TvShowSearchFragment : Fragment() {

    private val args:TvShowSearchFragmentArgs by navArgs()
    private lateinit var viewModelTvShow:TvShowViewModel
    private lateinit var viewModelMovie: MovieViewModel

    private lateinit var gridLinearLayoutSearch: GridLayoutManager
    private lateinit var adapterSearchTvShow: SecondTvShowSearchAdapter
    private lateinit var adapterSearchMovie: FirstMovieSearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelTvShow = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]
        viewModelMovie = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        gridLinearLayoutSearch = GridLayoutManager(requireActivity(),2)

        searchTextTitle.text = "Results for \"" + args.searchText + "\""

        getTvSearchResultRecyclerSetup()
        getMovieSearchResultRecyclerSetup()
    }

    private fun getMovieSearchResultRecyclerSetup() {
        rvSearch.layoutManager = gridLinearLayoutSearch
        rvSearch.setHasFixedSize(true)

        val tvTitle = args.searchText.toString()

        viewModelMovie.getMovieSearchResults(URL.api_key,tvTitle)
        viewModelMovie.searchMovieList.observe(viewLifecycleOwner) {
            adapterSearchMovie = FirstMovieSearchAdapter(it.results as ArrayList<Result>)
            rvSearch.adapter = adapterSearchMovie
            adapterSearchMovie.notifyDataSetChanged()
        }
    }

    private fun getTvSearchResultRecyclerSetup() {
        rvSearch.layoutManager = gridLinearLayoutSearch
        rvSearch.setHasFixedSize(true)

        val tvTitle = args.searchText.toString()

        viewModelTvShow.getTvSearchResults(URL.api_key,tvTitle)
        viewModelTvShow.searchTvShowList.observe(viewLifecycleOwner) {
            adapterSearchTvShow = SecondTvShowSearchAdapter(it.results as ArrayList<ResultTvShow>)
            rvSearch.adapter = adapterSearchTvShow
            adapterSearchTvShow.notifyDataSetChanged()
        }
    }
}