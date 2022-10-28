package com.flexath.celluloid.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.flexath.celluloid.R
import com.flexath.celluloid.adapters.tv_show.SecondTvShowSearchAdapter
import com.flexath.celluloid.data.database.URL
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.data.movie_viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_show_search.*

class TvShowSearchFragment : Fragment() {

    private val args:TvShowSearchFragmentArgs by navArgs()
    private lateinit var viewModelTvShow:TvShowViewModel

    private lateinit var gridLinearLayoutSearchTvShow: GridLayoutManager
    private lateinit var adapterSearchTvShow: SecondTvShowSearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelTvShow = ViewModelProvider(requireActivity())[TvShowViewModel::class.java]

        gridLinearLayoutSearchTvShow = GridLayoutManager(requireActivity(),2)

        searchTextTitle.text = "Results for \"" + args.searchTvShowText + "\""

        getTvSearchResultRecyclerSetup()
    }

    private fun getTvSearchResultRecyclerSetup() {
        rvTvShowSearch.layoutManager = gridLinearLayoutSearchTvShow
        rvTvShowSearch.setHasFixedSize(true)

        val tvTitle = args.searchTvShowText.toString()

        viewModelTvShow.getTvSearchResults(URL.api_key,tvTitle)
        viewModelTvShow.searchTvShowList.observe(viewLifecycleOwner) {
            adapterSearchTvShow = SecondTvShowSearchAdapter(it.results as ArrayList<ResultTvShow>)
            rvTvShowSearch.adapter = adapterSearchTvShow
            adapterSearchTvShow.notifyDataSetChanged()
        }
    }
}