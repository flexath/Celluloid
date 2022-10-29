package com.flexath.celluloid.ui.tvshow

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
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_third.*

class TvShowThirdFragment : Fragment() {

    private lateinit var viewModel:TvShowViewModel
    private val args:TvShowThirdFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thirdTvShowTitle.text = args.tvShowResult!!.name
        thirdTvShowFirstOnAirDate.text = " First Air Date - " + args.tvShowResult!!.first_air_date
        thirdTvShowDescription.text = args.tvShowResult!!.overview
        thirdTvShowPoster.load("https://image.tmdb.org/t/p/original"+args.tvShowResult!!.poster_path)

        getAllGenres()
    }

    private fun getAllGenres() {
        genreVisibility()
        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]
        viewModel.getAllGenres(URL.api_key)
        viewModel.genreList.observe(viewLifecycleOwner) {
            for(i in args.tvShowResult!!.genre_ids.indices) {
                for(j in it.genres.indices){
                    if(args.tvShowResult!!.genre_ids[i] == it.genres[j].id) {
                        when (i) {
                            0 -> thirdTvShowKDramaGenre1.text = it.genres[j].name
                            1 -> thirdTvShowKDramaGenre2.text = it.genres[j].name
                            2 -> thirdTvShowKDramaGenre3.text = it.genres[j].name
                            else -> break
                        }
                    }
                }
            }
        }
    }

    private fun genreVisibility() {
        when(args.tvShowResult!!.genre_ids.size) {
            0 -> {
                thirdTvShowKDramaGenre1.visibility = View.GONE
                thirdTvShowKDramaGenre2.visibility = View.GONE
                thirdTvShowKDramaGenre3.visibility = View.GONE
            }
            1 -> {
                thirdTvShowKDramaGenre2.visibility = View.GONE
                thirdTvShowKDramaGenre3.visibility = View.GONE
            }
            2 -> {
                thirdTvShowKDramaGenre3.visibility = View.GONE
            }
        }
    }
}