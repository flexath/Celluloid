package com.flexath.celluloid.ui.tvshow

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.tv_show.SecondTvShowOnAirTodayAdapter
import com.flexath.celluloid.adapters.tv_show.SecondTvShowTrendingThisWeekAdapter
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_second_kdrama.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TvShowSecondFragment : Fragment() {

    private lateinit var viewModel:TvShowViewModel

    private lateinit var horizontalLinearLayoutKDramaTrendingThisWeek:LinearLayoutManager
    private lateinit var horizontalLinearLayoutKDramaOnAirToday:LinearLayoutManager

    private lateinit var adpaterKDramaTrendingThisWeek:SecondTvShowTrendingThisWeekAdapter
    private lateinit var adpaterKDramaOnAirToday:SecondTvShowOnAirTodayAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_second_kdrama, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]

        horizontalLinearLayoutKDramaTrendingThisWeek = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)
        horizontalLinearLayoutKDramaOnAirToday = LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false)

        getKDramaTrendingThisWeekRecyclerSetup()
        getKDramaOnAirTodayRecyclerSetup()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getKDramaTrendingThisWeekRecyclerSetup() {
        rvKDramaTrendingThisWeek.layoutManager = horizontalLinearLayoutKDramaTrendingThisWeek
        rvKDramaTrendingThisWeek.setHasFixedSize(true)

        val previousSevenDays = LocalDate.now().minusDays(7)
        viewModel.getAllTrendingThisWeekKDrama(URL.api_key,"ko","popularity.desc",previousSevenDays.toString())
        viewModel.trendingThisWeekKDramaList.observe(viewLifecycleOwner) {
            adpaterKDramaTrendingThisWeek = SecondTvShowTrendingThisWeekAdapter(it.results as ArrayList<ResultTvShow>)
            rvKDramaTrendingThisWeek.adapter = adpaterKDramaTrendingThisWeek
            adpaterKDramaTrendingThisWeek.notifyDataSetChanged()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getKDramaOnAirTodayRecyclerSetup() {
        rvKDramaOnAirToday.layoutManager = horizontalLinearLayoutKDramaOnAirToday
        rvKDramaOnAirToday.setHasFixedSize(true)

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDateTime.now().format(formatter)
        viewModel.getAllOnAirTodayKDrama(URL.api_key,"ko","popularity.desc",currentDate,currentDate)
        viewModel.onAirTodayKDramaList.observe(viewLifecycleOwner) {
            adpaterKDramaOnAirToday = SecondTvShowOnAirTodayAdapter(it.results as ArrayList<ResultTvShow>)
            rvKDramaOnAirToday.adapter = adpaterKDramaOnAirToday
            adpaterKDramaOnAirToday.notifyDataSetChanged()
        }
    }
}