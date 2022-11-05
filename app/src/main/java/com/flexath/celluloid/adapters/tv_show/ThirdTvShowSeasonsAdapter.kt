package com.flexath.celluloid.adapters.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.retrofit.details.tv_show.Season
import com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails
import com.flexath.celluloid.databinding.TvSeasonsRvBinding
import com.flexath.celluloid.ui.tvshow.TvShowThirdFragmentDirections

class ThirdTvShowSeasonsAdapter(private val seasonsTvShowList: List<com.flexath.celluloid.data.retrofit.details.tv_show.Season>, private val tvShowDetails: com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails)
        :RecyclerView.Adapter<ThirdTvShowSeasonsAdapter.ViewHolder>() {

    var tvIdSwitch = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvSeasonsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = seasonsTvShowList[position]
        holder.binding.apply {
            tvShowSeasonName.text = item.name
            tvShowSeasonFirstOnAirDate.text = item.air_date
            tvShowSeasonNumberOfEpisodes.text = item.episode_count.toString()
            tvShowSeasonPoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }

        holder.itemView.setOnClickListener {
            tvIdSwitch = true
            val action = TvShowThirdFragmentDirections.tvShowThirdToFourthAction()
            action.tvShowSeason = item
            action.tvShowSeasonResult = tvShowDetails.id.toString()
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return seasonsTvShowList.size
    }

    inner class ViewHolder(val binding:TvSeasonsRvBinding) : RecyclerView.ViewHolder(binding.root)
}