package com.flexath.celluloid.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.CreditSwitch.Companion.switchOnMovie
import com.flexath.celluloid.adapters.movie.second.SecondMovieCastsAdapter
import com.flexath.celluloid.adapters.movie.second.SecondMovieCrewsAdapter
import com.flexath.celluloid.data.URL
import com.flexath.celluloid.data.movie_viewmodel.MovieViewModel
import com.flexath.celluloid.data.room.MovieEntity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_movie_second.*
import kotlinx.android.synthetic.main.modal_bottom_dialog.*

class MovieSecondFragment : Fragment() {

    private lateinit var viewModel:MovieViewModel
    private val args:MovieSecondFragmentArgs by navArgs()

    private lateinit var horizontalLinearLayoutMovieCasts:LinearLayoutManager
    private lateinit var horizontalLinearLayoutMovieCrews:LinearLayoutManager
    private lateinit var adapterMovieCasts:SecondMovieCastsAdapter
    private lateinit var adapterMovieCrews:SecondMovieCrewsAdapter

    private lateinit var movieEntity:MovieEntity

    companion object {
        private var redHeartOnMovie = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MovieViewModel::class.java]

        horizontalLinearLayoutMovieCasts = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutMovieCrews = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        // Details
        getMovieDetails()
        getBottomDialogMovieDetails()

        // Credits
        switchOnMovie = true
        viewModel.getMovieCredits(args.result!!.id, URL.api_key)
        getMovieCasts()
        getMovieCrews()

    }

    private fun getBottomDialogMovieDetails() {
        btnGetMoreDetails.setOnClickListener {
            val modalBottomDialog = BottomSheetDialog(requireActivity(),R.style.ModalBottomDialog)
            val bottomView = layoutInflater.inflate(R.layout.modal_bottom_dialog,null,false)
            modalBottomDialog.setContentView(bottomView)
            modalBottomDialog.setCancelable(true)

            viewModel.getMovieDetails(args.result!!.id,URL.api_key)
            viewModel.detailsMovieList.observe(viewLifecycleOwner) {
                modalBottomDialog.secondMovieLanguage.text = it.original_language
                modalBottomDialog.secondMovieStatus.text = it.status
                modalBottomDialog.secondMovieTagline.text = it.tagline
                modalBottomDialog.secondMovieHomePage.text = it.homepage

                modalBottomDialog.secondMovieType.visibility = View.GONE
                modalBottomDialog.secondMovieTypeLayout.visibility = View.GONE
                modalBottomDialog.secondMovieNetwork.visibility = View.GONE
                modalBottomDialog.secondMovieNetworkLayout.visibility = View.GONE

                modalBottomDialog.secondMovieBudget.text = it.budget.toString()
                modalBottomDialog.secondMovieRevenue.text = it.revenue.toLong().toString()
                getProductionCompanies(it,modalBottomDialog)
                getProductionCountries(it,modalBottomDialog)
            }

            val btnHide = modalBottomDialog.findViewById<Button>(R.id.btnHideDetails)
            btnHide?.setOnClickListener {
                modalBottomDialog.dismiss()
            }
            modalBottomDialog.show()
        }
    }

    private fun setMoviesFavourite() {
        viewModel.getAllMovieFavourites()
        viewModel.movieFavouriteList.observe(viewLifecycleOwner) { entity ->
            for(i in entity.indices) {
                if(args.result!!.id == entity[i].movie_id) {
                    secondMovieSaved.setImageResource(R.drawable.ic_red_heart)
                    redHeartOnMovie = true
                    break
                }
                else{
                    redHeartOnMovie = false
                }
            }
        }
    }

    private fun getMovieDetails() {

        viewModel.getMovieDetails(args.result!!.id,URL.api_key)
        viewModel.detailsMovieList.observe(viewLifecycleOwner) {
            Log.i("MovieId",it.id.toString())
            secondMoviePoster.load("https://image.tmdb.org/t/p/original"+it.poster_path)
            secondMovieTitle.text = it.original_title
            secondMovieReleaseDate.text = " Release Date - " + it.release_date
            secondMovieRunTime.text = " Run Time     - " + it.runtime + " min"
            genreVisibility(it)
            secondMovieDescription.text = it.overview

            movieEntity = MovieEntity(it.id,it.original_title,it.release_date,it.poster_path,args.result!!)

            setMoviesFavourite()

            secondMovieSaved.setOnClickListener { _ ->

                if(!redHeartOnMovie){
                    movieEntity = MovieEntity(it.id,it.original_title,it.release_date,it.poster_path,args.result!!)
                    viewModel.insertMovieFavourite(movieEntity)
                    secondMovieSaved.setImageResource(R.drawable.ic_red_heart)
                    Toast.makeText(requireActivity(),"Movie's saved", Toast.LENGTH_SHORT).show()
                    redHeartOnMovie = true
                }else{

                    viewModel.deleteMovieFavourite(movieEntity)
                    secondMovieSaved.setImageResource(R.drawable.ic_save)
                    Toast.makeText(requireActivity(),"Movie's unsaved", Toast.LENGTH_SHORT).show()
                    redHeartOnMovie = false
                }
            }
        }
    }

    private fun getMovieCasts() {
        rvMovieCasts.layoutManager = horizontalLinearLayoutMovieCasts
        rvMovieCasts.setHasFixedSize(true)
        viewModel.creditsMovieList.observe(viewLifecycleOwner) {
            adapterMovieCasts = SecondMovieCastsAdapter(it.cast as ArrayList<com.flexath.celluloid.data.retrofit.credits.Cast>)
            rvMovieCasts.adapter = adapterMovieCasts
            adapterMovieCasts.notifyDataSetChanged()
        }
    }

    private fun getMovieCrews() {
        rvMovieCrews.layoutManager = horizontalLinearLayoutMovieCrews
        rvMovieCrews.setHasFixedSize(true)
        viewModel.creditsMovieList.observe(viewLifecycleOwner) {
            adapterMovieCrews = SecondMovieCrewsAdapter(it.crew as ArrayList<com.flexath.celluloid.data.retrofit.credits.Crew>)
            getDirectorName(it)
            rvMovieCrews.adapter = adapterMovieCrews
            adapterMovieCrews.notifyDataSetChanged()
        }
    }

    private fun getDirectorName(credits: com.flexath.celluloid.data.retrofit.credits.Credits) {
        for(i in credits.crew.indices) {
            if(credits.crew[i].job == "Director") {
                movieDirectorName.text = credits.crew[i].original_name
                movieDirectorProfilePicture.load("https://image.tmdb.org/t/p/original"+credits.crew[i].profile_path)
            }
        }
    }

    private fun getProductionCompanies(details: com.flexath.celluloid.data.retrofit.details.movie.Details, bottomDialog: BottomSheetDialog) {
        if(details.production_companies.isEmpty()) {
            bottomDialog.secondMovieProductionCompanies.text = "-"
        }
        else {
            bottomDialog.secondMovieProductionCompanies.text = ""
            for(i in details.production_companies.indices) {
                bottomDialog.secondMovieProductionCompanies.append(details.production_companies[i].name + " , ")
            }
            bottomDialog.secondMovieProductionCompanies.text = StringBuilder(bottomDialog.secondMovieProductionCompanies.text.toString())
                .deleteRange(bottomDialog.secondMovieProductionCompanies.text.lastIndex-1,
                    bottomDialog.secondMovieProductionCompanies.text.lastIndex)
        }
    }

    private fun getProductionCountries(details: com.flexath.celluloid.data.retrofit.details.movie.Details, bottomDialog: BottomSheetDialog) {
        if(details.production_countries.isEmpty()){
            bottomDialog.secondMovieProductionCountries.text = "-"
        }
        else {
            bottomDialog.secondMovieProductionCountries.text = ""
            for(i in details.production_countries.indices) {
                bottomDialog.secondMovieProductionCountries.append(details.production_countries[i].name + " , ")
            }
            bottomDialog.secondMovieProductionCountries.text = StringBuilder(bottomDialog.secondMovieProductionCountries.text.toString())
                    .deleteRange(bottomDialog.secondMovieProductionCountries.text.lastIndex-1,bottomDialog.secondMovieProductionCountries.text.lastIndex)
        }
    }


    private fun genreVisibility(details: com.flexath.celluloid.data.retrofit.details.movie.Details) {
        when(details.genres.size) {
            0 -> {
                secondMovieGenre1.visibility = View.GONE
                secondMovieGenre2.visibility = View.GONE
                secondMovieGenre3.visibility = View.GONE
            }
            1 -> {
                secondMovieGenre1.text = details.genres[0].name
                secondMovieGenre2.visibility = View.GONE
                secondMovieGenre3.visibility = View.GONE
            }
            2 -> {
                secondMovieGenre1.text = details.genres[0].name
                secondMovieGenre2.text = details.genres[1].name
                secondMovieGenre3.visibility = View.GONE
            }
            3 -> {
                secondMovieGenre1.text = details.genres[0].name
                secondMovieGenre2.text = details.genres[1].name
                secondMovieGenre3.text = details.genres[2].name
            }
        }
    }

}