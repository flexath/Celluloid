package com.flexath.celluloid.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.movie.second.SecondMovieCastsAdapter
import com.flexath.celluloid.adapters.movie.second.SecondMovieCrewsAdapter
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.database.credits.Cast
import com.flexath.celluloid.data.database.credits.Crew
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_second.*
import kotlinx.android.synthetic.main.movie_casts_rv.*

class MovieSecondFragment : Fragment() {

    private lateinit var viewModel:MovieViewModel
    private val args:MovieSecondFragmentArgs by navArgs()

    private lateinit var horizontalLinearLayoutMovieCasts:LinearLayoutManager
    private lateinit var horizontalLinearLayoutMovieCrews:LinearLayoutManager
    private lateinit var adapterMovieCasts:SecondMovieCastsAdapter
    private lateinit var adapterMovieCrews:SecondMovieCrewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        horizontalLinearLayoutMovieCasts = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutMovieCrews = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        secondMovieTitle.text = args.result!!.title
        secondMovieReleaseDate.text = " Release Date - " + args.result!!.release_date
        secondMovieDescription.text = args.result!!.overview
        secondMoviePoster.load("https://image.tmdb.org/t/p/original"+args.result!!.poster_path)

        getAllGenres()

        viewModel.getMovieCredits(args.result!!.id,URL.api_key)
        getMovieCasts()
        getMovieCrews()
    }

    private fun getAllGenres() {
        genreVisibility()

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

    private fun getMovieCasts() {
        rvMovieCasts.layoutManager = horizontalLinearLayoutMovieCasts
        rvMovieCasts.setHasFixedSize(true)


        viewModel.creditsMovieList.observe(viewLifecycleOwner) {
            adapterMovieCasts = SecondMovieCastsAdapter(it.cast as ArrayList<Cast>)
            rvMovieCasts.adapter = adapterMovieCasts
            adapterMovieCasts.notifyDataSetChanged()
        }
    }

    private fun getMovieCrews() {
        rvMovieCrews.layoutManager = horizontalLinearLayoutMovieCrews
        rvMovieCrews.setHasFixedSize(true)

        viewModel.creditsMovieList.observe(viewLifecycleOwner) {
            adapterMovieCrews = SecondMovieCrewsAdapter(it.crew as ArrayList<Crew>)

            for(i in it.crew.indices) {
                if(it.crew[i].job == "Director") {
                    movieDirectorName.text = it.crew[i].original_name
                    movieDirectorProfilePicture.load("https://image.tmdb.org/t/p/original"+it.crew[i].profile_path)
                }
            }
            rvMovieCrews.adapter = adapterMovieCrews
            adapterMovieCrews.notifyDataSetChanged()
        }
    }

    private fun genreVisibility() {
        when(args.result!!.genre_ids!!.size) {
            0 -> {
                secondMovieGenre1.visibility = View.GONE
                secondMovieGenre2.visibility = View.GONE
                secondMovieGenre3.visibility = View.GONE
            }
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