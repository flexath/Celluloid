package com.flexath.celluloid.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.flexath.celluloid.R
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_second.*

class MovieSecondFragment : Fragment() {

    private lateinit var viewModel:MovieViewModel
    private val args:MovieSecondFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        secondMovieTitle.text = args.result!!.title
        secondMovieReleaseDate.text = " Release Date - " + args.result!!.release_date
        secondMovieDescription.text = args.result!!.overview
        secondMoviePoster.load("https://image.tmdb.org/t/p/original"+args.result!!.poster_path)

        getAllGenres()
    }

    private fun getAllGenres() {
        genreVisibility()
        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]
        viewModel.getAllGenres(URL.api_key)
        viewModel.genreList.observe(viewLifecycleOwner) {
            for(i in args.result!!.genre_ids!!.indices) {
                for(j in it.genres.indices){
                    if(args.result!!.genre_ids!![i] == it.genres[j].id) {
                        when (i) {
                            0 -> secondMovieGenre1.text = it.genres[j].name
                            1 -> secondMovieGenre2.text = it.genres[j].name
                            2 -> secondMovieGenre3.text = it.genres[j].name
                            else -> break
                        }
                    }
                }
            }
        }
    }

    private fun genreVisibility() {
        when(args.result!!.genre_ids!!.size) {
            1 -> {
                secondMovieGenre2.visibility = View.GONE
                secondMovieGenre3.visibility = View.GONE
            }
            2 -> {
                secondMovieGenre3.visibility = View.GONE
            }
        }
    }

}