package com.flexath.celluloid.ui.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.saved.FirstSavedTvShowAdapter
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_saved_first.*

class TvShowSavedFirstFragment : Fragment() {

    private lateinit var gridLayoutSavedTvShow:GridLayoutManager
    private lateinit var adapterSavedTvShow:FirstSavedTvShowAdapter

    private lateinit var viewModel:TvShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_saved_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridLayoutSavedTvShow = GridLayoutManager(requireActivity(),2)

        viewModel = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]

        savedTvShowRecyclerSetup()
    }

    private fun savedTvShowRecyclerSetup() {
        rvSavedTvShow.layoutManager = gridLayoutSavedTvShow
        rvSavedTvShow.setHasFixedSize(true)

        viewModel.getAllTvShowFavourites()
        viewModel.tvShowFavouriteList.observe(viewLifecycleOwner) {
            adapterSavedTvShow = FirstSavedTvShowAdapter(it)
            rvSavedTvShow.adapter = adapterSavedTvShow
            adapterSavedTvShow.notifyDataSetChanged()
        }
    }
}