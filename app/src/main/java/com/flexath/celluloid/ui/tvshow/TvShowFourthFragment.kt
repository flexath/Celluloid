package com.flexath.celluloid.ui.tvshow

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.CreditSwitch.Companion.switchOnMovie
import com.flexath.celluloid.adapters.movie.second.SecondMovieCastsAdapter
import com.flexath.celluloid.adapters.movie.second.SecondMovieCrewsAdapter
import com.flexath.celluloid.adapters.tv_show.ThirdTvShowEpisodesAdapter
import com.flexath.celluloid.data.URL
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_fourth.*

class TvShowFourthFragment : Fragment() {

    private val args:TvShowFourthFragmentArgs by navArgs()
    private lateinit var viewModel:TvShowViewModel

    private lateinit var linearLayoutEpisode:LinearLayoutManager
    private lateinit var horizontalLinearLayoutCasts:LinearLayoutManager
    private lateinit var horizontalLinearLayoutCrews:LinearLayoutManager
    private lateinit var adapterTvShowEpisode:ThirdTvShowEpisodesAdapter
    private lateinit var adapterTvShowCasts:SecondMovieCastsAdapter
    private lateinit var adapterTvShowCrews:SecondMovieCrewsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]

        linearLayoutEpisode = LinearLayoutManager(requireActivity())
        horizontalLinearLayoutCasts = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutCrews = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        switchOnMovie = false

        getTvShowSeasonDetails()
        getTvShowSeasonCredits()

        Log.i("SwitchTvShow",switchOnMovie.toString())
    }

    private fun getTvShowSeasonDetails() {
        rvTvShowSeasonEpisode.layoutManager = linearLayoutEpisode
        rvTvShowSeasonEpisode.setHasFixedSize(true)

        viewModel.getTvShowSeason(args.tvShowSeasonResult!!.toInt(),args.tvShowSeason!!.season_number,
            URL.api_key)
        viewModel.seasonTvShowList.observe(viewLifecycleOwner) {
            fourthTvShowSeasonName.text = it.name
            if(it.overview == "") {
                fourthTvShowSeasonOverviewTxt.visibility = View.GONE
                fourthTvShowSeasonOverview.visibility = View.GONE
            }else{
                fourthTvShowSeasonOverview.text = it.overview
            }

            adapterTvShowEpisode = ThirdTvShowEpisodesAdapter(it.episodes)
            rvTvShowSeasonEpisode.adapter = adapterTvShowEpisode
            adapterTvShowEpisode.notifyDataSetChanged()
        }
    }

    private fun getTvShowSeasonCredits() {
        viewModel.getTvShowSeasonCredits(args.tvShowSeasonResult!!.toInt(),args.tvShowSeason!!.season_number,
            URL.api_key)
        getTvShowSeasonCastsRecyclerSetup()
        getTvShowSeasonCrewsRecyclerSetup()
    }

    private fun getTvShowSeasonCastsRecyclerSetup() {
        rvTvShowSeasonCasts.layoutManager = horizontalLinearLayoutCasts
        rvTvShowSeasonCasts.setHasFixedSize(true)

        viewModel.creditsTvShowList.observe(viewLifecycleOwner) {
            adapterTvShowCasts = SecondMovieCastsAdapter(it.cast as ArrayList<com.flexath.celluloid.data.retrofit.credits.Cast>)
            rvTvShowSeasonCasts.adapter = adapterTvShowCasts
            adapterTvShowCasts.notifyDataSetChanged()
        }
    }

    private fun getTvShowSeasonCrewsRecyclerSetup() {
        rvTvShowSeasonCrews.layoutManager = horizontalLinearLayoutCrews
        rvTvShowSeasonCrews.setHasFixedSize(true)

        viewModel.creditsTvShowList.observe(viewLifecycleOwner) {
            adapterTvShowCrews = SecondMovieCrewsAdapter(it.crew as ArrayList<com.flexath.celluloid.data.retrofit.credits.Crew>)
            rvTvShowSeasonCrews.adapter = adapterTvShowCrews
            adapterTvShowCrews.notifyDataSetChanged()
        }
    }
}