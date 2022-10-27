package com.flexath.celluloid.ui.tvshow

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.tv_show.SecondTvShowOnAirTodayAdapter
import com.flexath.celluloid.adapters.tv_show.SecondTvShowTopRatedAdapter
import com.flexath.celluloid.adapters.tv_show.SecondTvShowTrendingThisWeekAdapter
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_first.*
import kotlinx.android.synthetic.main.fragment_tv_show_second.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TvShowSecondFragment : Fragment() {

    private lateinit var viewModel:TvShowViewModel

    private lateinit var horizontalLinearLayoutTvShowTrendingThisWeek:LinearLayoutManager
    private lateinit var horizontalLinearLayoutTvShowOnAirToday:LinearLayoutManager
    private lateinit var horizontalLinearLayoutTvShowTopRated:LinearLayoutManager

    private lateinit var adpaterTvShowTrendingThisWeek:SecondTvShowTrendingThisWeekAdapter
    private lateinit var adpaterTvShowOnAirToday:SecondTvShowOnAirTodayAdapter
    private lateinit var adpaterTvShowTopRated:SecondTvShowTopRatedAdapter

    private val args:TvShowSecondFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_second, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]

        horizontalLinearLayoutTvShowTrendingThisWeek = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutTvShowOnAirToday = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutTvShowTopRated = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        getAllRecyclers()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getAllRecyclers() {
        when (args.tvShowLanguage) {
            "american" -> {
                getTvShowTrendingThisWeekRecyclerSetup("en")
                getTvShowOnAirTodayRecyclerSetup("en")
                getTvShowTopRatedRecyclerSetup("en")
            }
            "korea" -> {
                getTvShowTrendingThisWeekRecyclerSetup("ko")
                getTvShowOnAirTodayRecyclerSetup("ko")
                getTvShowTopRatedRecyclerSetup("ko")
            }
            "thailand" -> {
                getTvShowTrendingThisWeekRecyclerSetup("th")
                getTvShowOnAirTodayRecyclerSetup("th")
                getTvShowTopRatedRecyclerSetup("th")
            }
            else -> {
                getTvShowTrendingThisWeekRecyclerSetup("hi")
                getTvShowOnAirTodayRecyclerSetup("hi")
                getTvShowTopRatedRecyclerSetup("hi")
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTvShowTrendingThisWeekRecyclerSetup(nation:String) {
        rvKDramaTrendingThisWeek.layoutManager = horizontalLinearLayoutTvShowTrendingThisWeek
        rvKDramaTrendingThisWeek.setHasFixedSize(true)

        val previousSevenDays = LocalDate.now().minusDays(7)
        viewModel.getAllTrendingThisWeekTvShow(URL.api_key,nation,"popularity.desc",previousSevenDays.toString())
        viewModel.trendingThisWeekTvShowList.observe(viewLifecycleOwner) {
            adpaterTvShowTrendingThisWeek = SecondTvShowTrendingThisWeekAdapter(it.results as ArrayList<ResultTvShow>)
            rvKDramaTrendingThisWeek.adapter = adpaterTvShowTrendingThisWeek
            adpaterTvShowTrendingThisWeek.notifyDataSetChanged()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTvShowOnAirTodayRecyclerSetup(nation:String) {
        rvKDramaOnAirToday.layoutManager = horizontalLinearLayoutTvShowOnAirToday
        rvKDramaOnAirToday.setHasFixedSize(true)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDateTime.now().format(formatter)
        viewModel.getAllOnAirTodayTvShow(URL.api_key,nation,"popularity.desc",currentDate,currentDate)
        viewModel.onAirTodayTvShowList.observe(viewLifecycleOwner) {
            adpaterTvShowOnAirToday = SecondTvShowOnAirTodayAdapter(it.results as ArrayList<ResultTvShow>)
            rvKDramaOnAirToday.adapter = adpaterTvShowOnAirToday
            adpaterTvShowOnAirToday.notifyDataSetChanged()
        }
    }

    private fun getTvShowTopRatedRecyclerSetup(nation:String) {
        rvTvShowTopRated.layoutManager = horizontalLinearLayoutTvShowTopRated
        rvTvShowTopRated.setHasFixedSize(true)

        viewModel.getAllTopRatedTvShow(URL.api_key,nation,300,"vote_average.desc",1)
        viewModel.topRatedTvShowList.observe(viewLifecycleOwner) {
            adpaterTvShowTopRated = SecondTvShowTopRatedAdapter(it.results as ArrayList<ResultTvShow>)
            rvTvShowTopRated.adapter = adpaterTvShowTopRated
            adpaterTvShowTopRated.notifyDataSetChanged()
        }
    }
}