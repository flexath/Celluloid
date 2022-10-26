package com.flexath.celluloid.adapters.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.databinding.TvKdramaTrendingRvBinding
import com.flexath.celluloid.ui.tvshow.TvShowSecondFragmentDirections

class SecondTvShowTrendingThisWeekAdapter(private val trendingThisWeekTvShowList: ArrayList<ResultTvShow>)
        :RecyclerView.Adapter<SecondTvShowTrendingThisWeekAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvKdramaTrendingRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = trendingThisWeekTvShowList[position]
        holder.binding.apply {
            tvShowKDramaTrendingTitle.text = item.name
            tvShowKDramaTrendingFirstOnAirDate.text = item.first_air_date
            tvShowKDramaTrendingPoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }

        holder.itemView.setOnClickListener {
            val action = TvShowSecondFragmentDirections.tvShowKDramaSecondToThirdAction()
            action.tvShowResult = item
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return trendingThisWeekTvShowList.size
    }

    inner class ViewHolder(val binding:TvKdramaTrendingRvBinding) : RecyclerView.ViewHolder(binding.root)
}