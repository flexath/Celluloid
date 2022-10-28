package com.flexath.celluloid.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.flexath.celluloid.R
import kotlinx.android.synthetic.main.fragment_tv_show_first.*

class TvShowFirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchText = requireActivity().findViewById<EditText>(R.id.searchTvShow)

        searchTvShow.setOnClickListener {
            val action = TvShowFirstFragmentDirections.tvShowFirstToSearchAction()
            action.searchTvShowText = searchText.text.toString()
            findNavController().navigate(action)
        }

        tvShowAmerican.setOnClickListener {
            tvShows("american")
        }

        tvShowKorean.setOnClickListener {
            tvShows("korea")
        }

        tvShowThai.setOnClickListener {
            tvShows("thailand")
        }

        tvShowIndian.setOnClickListener {
            tvShows("india")
        }
    }

    private fun tvShows(nation:String) {
        val action = TvShowFirstFragmentDirections.tvShowFirstToSecondAction()
        action.tvShowLanguage = nation
        findNavController().navigate(action)
    }
}