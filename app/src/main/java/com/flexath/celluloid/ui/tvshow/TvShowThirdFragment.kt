package com.flexath.celluloid.ui.tvshow

import android.os.Bundle
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
import com.flexath.celluloid.adapters.tv_show.ThirdTvShowCreatorsAdapter
import com.flexath.celluloid.adapters.tv_show.ThirdTvShowSeasonsAdapter
import com.flexath.celluloid.data.URL
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import com.flexath.celluloid.data.room.TvShowEntity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_tv_show_third.*
import kotlinx.android.synthetic.main.modal_bottom_dialog.*

class TvShowThirdFragment : Fragment() {

    private lateinit var viewModel:TvShowViewModel
    private val args:TvShowThirdFragmentArgs by navArgs()

    private lateinit var horizontalLinearLayoutCreators:LinearLayoutManager
    private lateinit var horizontalLinearLayoutSeasons:LinearLayoutManager
    private lateinit var adapterTvShowCreators:ThirdTvShowCreatorsAdapter
    private lateinit var adapterTvShowSeasons:ThirdTvShowSeasonsAdapter

    private lateinit var tvShowEntity: TvShowEntity

    companion object{
        private var redHeartOnTvShow = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]
        viewModel.getTvShowDetails(args.tvShowResult!!.id, URL.api_key)

        horizontalLinearLayoutCreators = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutSeasons = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        getTvShowDetails()
        getBottomDialogMovieDetails()
        getTvShowSeasonsRecyclerSetup()
        getTvShowCreatorsRecyclerSetup()

    }

    private fun getBottomDialogMovieDetails() {
        btnTvShowGetMoreDetails.setOnClickListener {
            val modalBottomDialog = BottomSheetDialog(requireActivity(),R.style.ModalBottomDialog)
            val bottomView = layoutInflater.inflate(R.layout.modal_bottom_dialog,null,false)
            modalBottomDialog.setContentView(bottomView)
            modalBottomDialog.setCancelable(true)

            viewModel.getTvShowDetails(args.tvShowResult!!.id,URL.api_key)
            viewModel.detailsTvList.observe(viewLifecycleOwner) {
                modalBottomDialog.secondMovieLanguage.text = it.original_language
                modalBottomDialog.secondMovieStatus.text = it.status
                modalBottomDialog.secondMovieTagline.text = it.tagline
                modalBottomDialog.secondMovieHomePage.text = it.homepage
                modalBottomDialog.secondMovieType.text = it.type
                getTvShowNetwork(it,modalBottomDialog)
                getTvShowProductionCompanies(it,modalBottomDialog)
                getTvShowProductionCountries(it,modalBottomDialog)

                modalBottomDialog.secondMovieBudgetLayout.visibility = View.GONE
                modalBottomDialog.secondMovieBudget.visibility = View.GONE
                modalBottomDialog.secondMovieRevenueLayout.visibility = View.GONE
                modalBottomDialog.secondMovieRevenue.visibility = View.GONE
            }

            val btnHide = modalBottomDialog.findViewById<Button>(R.id.btnHideDetails)
            btnHide?.setOnClickListener {
                modalBottomDialog.dismiss()
            }
            modalBottomDialog.show()
        }
    }

    private fun getTvShowSeasonsRecyclerSetup() {
        rvTvShowSeasons.layoutManager = horizontalLinearLayoutSeasons
        rvTvShowSeasons.setHasFixedSize(true)

        viewModel.detailsTvList.observe(viewLifecycleOwner) {
            adapterTvShowSeasons = ThirdTvShowSeasonsAdapter(it.seasons,it)
            rvTvShowSeasons.adapter = adapterTvShowSeasons
            adapterTvShowSeasons.notifyDataSetChanged()
        }
    }

    private fun getTvShowCreatorsRecyclerSetup() {
        rvTvShowCreators.layoutManager = horizontalLinearLayoutCreators
        rvTvShowCreators.setHasFixedSize(true)

        viewModel.detailsTvList.observe(viewLifecycleOwner) {
            adapterTvShowCreators = ThirdTvShowCreatorsAdapter(it.created_by)
            rvTvShowCreators.adapter = adapterTvShowCreators
            adapterTvShowCreators.notifyDataSetChanged()
        }
    }

    private fun setTvShowFavourite() {
        viewModel.getAllTvShowFavourites()
        viewModel.tvShowFavouriteList.observe(viewLifecycleOwner) { entity ->
            for(i in entity.indices) {
                if(args.tvShowResult!!.id == entity[i].tv_id) {
                    thirdTvShowSaved.setImageResource(R.drawable.ic_red_heart)
                    redHeartOnTvShow = true
                    break
                }
                else{
                    redHeartOnTvShow = false
                }
            }
        }
    }

    private fun getTvShowDetails() {

        viewModel.detailsTvList.observe(viewLifecycleOwner) {
            thirdTvShowPoster.load("https://image.tmdb.org/t/p/original"+it.poster_path)
            thirdTvShowTitle.text = it.name
            thirdTvShowFirstAirDate.text = "- " + it.first_air_date
            thirdTvShowLastAirDate.text = "- " + it.last_air_date
            getRuntimePerEpisode(it)
            thirdTvShowNumberOfSeasons.text = "- " + it.number_of_seasons.toString()
            thirdTvShowNumberOfEpisodes.text = "- " + it.number_of_episodes.toString()
            genreTvShowVisibility(it)
            thirdTvShowDescription.text = it.overview

            tvShowEntity = TvShowEntity(it.id,it.name,it.first_air_date,it.poster_path,args.tvShowResult!!)

            setTvShowFavourite()

            thirdTvShowSaved.setOnClickListener {

                redHeartOnTvShow = if(!redHeartOnTvShow) {
                    viewModel.insertTvShowFavourite(tvShowEntity)
                    thirdTvShowSaved.setImageResource(R.drawable.ic_red_heart)
                    Toast.makeText(requireActivity(),"Tv Show's saved", Toast.LENGTH_SHORT).show()
                    true

                }else{
                    viewModel.deleteTvShowFavourite(tvShowEntity)
                    thirdTvShowSaved.setImageResource(R.drawable.ic_save)
                    Toast.makeText(requireActivity(),"Tv Show's unsaved", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }

    private fun getTvShowNetwork(details: com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails, bottomDialog: BottomSheetDialog) {
        if(details.networks.isEmpty()) {
            bottomDialog.secondMovieNetwork.text = "-"
        }
        else {
            bottomDialog.secondMovieNetwork.text = ""
            for(i in details.networks.indices) {
                bottomDialog.secondMovieNetwork.append(details.networks[i].name + " , ")
            }
            bottomDialog.secondMovieNetwork.text = StringBuilder(bottomDialog.secondMovieNetwork.text.toString())
                .deleteRange(bottomDialog.secondMovieNetwork.text.lastIndex-1,
                    bottomDialog.secondMovieNetwork.text.lastIndex)
        }
    }

    private fun getRuntimePerEpisode(details: com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails) {
        if(details.episode_run_time!!.isEmpty()){
            thirdTvShowEpisodeRunTime.text = "-"
        }
        else {
            thirdTvShowEpisodeRunTime.text = ""
            for(i in details.episode_run_time.indices) {
                thirdTvShowEpisodeRunTime.text = "- "
                thirdTvShowEpisodeRunTime.append(details.episode_run_time[i].toString() + " min , ")
            }
            thirdTvShowEpisodeRunTime.text = StringBuilder(thirdTvShowEpisodeRunTime.text.toString())
                .deleteRange(thirdTvShowEpisodeRunTime.text.lastIndex-1,thirdTvShowEpisodeRunTime.text.lastIndex)
        }
    }

    private fun getTvShowProductionCompanies(details: com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails, bottomDialog: BottomSheetDialog) {
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

    private fun getTvShowProductionCountries(details: com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails, bottomDialog: BottomSheetDialog) {
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

    private fun genreTvShowVisibility(details: com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails) {
        when(details.genres!!.size) {
            0 -> {
                thirdTvShowGenre1.visibility = View.GONE
                thirdTvShowGenre2.visibility = View.GONE
                thirdTvShowGenre3.visibility = View.GONE
            }
            1 -> {
                thirdTvShowGenre1.text = details.genres[0].name
                thirdTvShowGenre2.visibility = View.GONE
                thirdTvShowGenre3.visibility = View.GONE
            }
            2 -> {
                thirdTvShowGenre1.text = details.genres[0].name
                thirdTvShowGenre2.text = details.genres[1].name
                thirdTvShowGenre3.visibility = View.GONE
            }
            3 -> {
                thirdTvShowGenre1.text = details.genres[0].name
                thirdTvShowGenre2.text = details.genres[1].name
                thirdTvShowGenre3.text = details.genres[2].name
            }
        }
    }
}