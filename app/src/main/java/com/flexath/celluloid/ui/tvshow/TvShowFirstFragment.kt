package com.flexath.celluloid.ui.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.flexath.celluloid.R
import kotlinx.android.synthetic.main.fragment_tv_show_first.*

class TvShowFirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowAmerican.setOnClickListener {
            tvShows()
        }

        tvShowKorean.setOnClickListener {
            tvShows()
        }

        tvShowThai.setOnClickListener {
            tvShows()
        }

        tvShowIndian.setOnClickListener {
            tvShows()
        }
    }

    private fun tvShows() {
        val action = TvShowFirstFragmentDirections.tvShowFirstToSecondAction()
        findNavController().navigate(action)
    }
}