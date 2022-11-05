package com.flexath.celluloid.adapters.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.databinding.TvEpisodeRvBinding

class ThirdTvShowEpisodesAdapter(private val episodesTvShowList: List<com.flexath.celluloid.data.retrofit.tv_show.seasons.Episode>)
        :RecyclerView.Adapter<ThirdTvShowEpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvEpisodeRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = episodesTvShowList[position]
        holder.binding.apply {
            tvShowEpisodeNumber.text = item.episode_number.toString()
            tvShowEpisodeName.text = item.name
            tvShowEpisodeAirDate.text = item.air_date
            tvShowEpisodeRuntime.text = item.runtime.toString() + " min"
            tvShowEpisodePoster.load("https://image.tmdb.org/t/p/original"+item.still_path)
        }
    }

    override fun getItemCount(): Int {
        return episodesTvShowList.size
    }

    inner class ViewHolder(val binding:TvEpisodeRvBinding) : RecyclerView.ViewHolder(binding.root)
}