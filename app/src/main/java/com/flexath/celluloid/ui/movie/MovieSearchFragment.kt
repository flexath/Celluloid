package com.flexath.celluloid.ui.movie

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
import com.flexath.celluloid.data.URL
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_search.*

class MovieSearchFragment : Fragment() {

    private val args: MovieSearchFragmentArgs by navArgs()
    private lateinit var viewModelMovie: MovieViewModel

    private lateinit var gridLinearLayoutSearchMovie: GridLayoutManager
    private lateinit var adapterSearchMovie: FirstMovieSearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelMovie = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        gridLinearLayoutSearchMovie = GridLayoutManager(requireActivity(),2)

        searchMovieTextTitle.text = "Results for \"" + args.searchMovieText + "\""

        getMovieSearchResultRecyclerSetup()
    }

    private fun getMovieSearchResultRecyclerSetup() {
        rvMovieSearch.layoutManager = gridLinearLayoutSearchMovie
        rvMovieSearch.setHasFixedSize(true)

        val tvTitle = args.searchMovieText.toString()

        viewModelMovie.getMovieSearchResults(URL.api_key,tvTitle)
        viewModelMovie.searchMovieList.observe(viewLifecycleOwner) {
            adapterSearchMovie = FirstMovieSearchAdapter(it.results as ArrayList<com.flexath.celluloid.data.retrofit.movie.Result>)
            rvMovieSearch.adapter = adapterSearchMovie
            adapterSearchMovie.notifyDataSetChanged()
        }
    }
}