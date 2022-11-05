package com.flexath.celluloid.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.saved.FirstSavedMovieAdapter
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_saved_first.*

class MovieSavedFirstFragment : Fragment() {

    private lateinit var gridLayoutSavedMovie:GridLayoutManager
    private lateinit var adapterSavedMovie:FirstSavedMovieAdapter

    private lateinit var viewModel:MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_saved_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutSavedMovie = GridLayoutManager(requireActivity(),2)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        savedMovieRecyclerSetup()
    }

    private fun savedMovieRecyclerSetup() {
        rvSavedMovie.layoutManager = gridLayoutSavedMovie
        rvSavedMovie.setHasFixedSize(true)

        viewModel.getAllMovieFavourites()
        viewModel.movieFavouriteList.observe(viewLifecycleOwner) {
            adapterSavedMovie = FirstSavedMovieAdapter(it)
            rvSavedMovie.adapter = adapterSavedMovie
            adapterSavedMovie.notifyDataSetChanged()
        }
    }
}