package com.flexath.celluloid.adapters.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.databinding.TvOnairTodayRvBinding
import com.flexath.celluloid.ui.tvshow.TvShowSecondFragmentDirections

class SecondTvShowOnAirTodayAdapter(private val onAirTodayTvShowList: ArrayList<com.flexath.celluloid.data.retrofit.tv_show.ResultTvShow>)
        :RecyclerView.Adapter<SecondTvShowOnAirTodayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvOnairTodayRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = onAirTodayTvShowList[position]
        holder.binding.apply {
            tvShowKDramaOnAirTodayTitle.text = item.name
            tvShowKDramaOnAirTodayFirstOnAirDate.text = item.first_air_date
            tvShowKDramaOnAirTodayPoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }

        holder.itemView.setOnClickListener {
            val action = TvShowSecondFragmentDirections.tvShowKDramaSecondToThirdAction()
            action.tvShowResult = item
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return onAirTodayTvShowList.size
    }

    inner class ViewHolder(val binding:TvOnairTodayRvBinding) : RecyclerView.ViewHolder(binding.root)
}