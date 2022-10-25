package com.flexath.celluloid.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.flexath.celluloid.R
import kotlinx.android.synthetic.main.fragment_popular_second.*

class PopularSecondFragment : Fragment() {

    private val args:PopularSecondFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_popular_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = args.result!!

        secondMovieTitle.text = result.title
        secondMovieReleaseDate.text = "Release Date - " + result.release_date
        secondMovieDescription.text = result.overview
        secondMoviePoster.load("https://image.tmdb.org/t/p/original"+result.poster_path)
    }
}