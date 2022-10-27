package com.flexath.celluloid.adapters.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.databinding.TvTopRatedRvBinding
import com.flexath.celluloid.ui.tvshow.TvShowSecondFragmentDirections

class SecondTvShowTopRatedAdapter(private val topRatedTvShowList: ArrayList<ResultTvShow>)
        :RecyclerView.Adapter<SecondTvShowTopRatedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvTopRatedRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = topRatedTvShowList[position]
        holder.binding.apply {
            tvShowTopRatedTitle.text = item.name
            tvShowTopRatedFirstOnAirDate.text = item.first_air_date
            tvShowTopRatedPoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }

        holder.itemView.setOnClickListener {
            val action = TvShowSecondFragmentDirections.tvShowKDramaSecondToThirdAction()
            action.tvShowResult = item
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return topRatedTvShowList.size
    }

    inner class ViewHolder(val binding:TvTopRatedRvBinding) : RecyclerView.ViewHolder(binding.root)
}