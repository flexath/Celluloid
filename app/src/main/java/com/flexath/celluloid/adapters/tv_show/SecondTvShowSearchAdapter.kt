package com.flexath.celluloid.adapters.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.databinding.TvSearchRvBinding
import com.flexath.celluloid.ui.tvshow.TvShowSearchFragmentDirections
import com.flexath.celluloid.ui.tvshow.TvShowSecondFragmentDirections

class SecondTvShowSearchAdapter(private val searchTvShowList: ArrayList<ResultTvShow>)
        :RecyclerView.Adapter<SecondTvShowSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvSearchRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchTvShowList[position]
        holder.binding.apply {
            tvShowSearchTitle.text = item.name
            tvShowSearchFirstOnAirDate.text = item.first_air_date
            tvShowSearchPoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }

        holder.itemView.setOnClickListener {
            val action = TvShowSearchFragmentDirections.tvShowSearchToThirdAction()
            action.tvShowResult = item
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return searchTvShowList.size
    }
    fun clearTv() {
        searchTvShowList.clear()
    }

    inner class ViewHolder(val binding:TvSearchRvBinding) : RecyclerView.ViewHolder(binding.root)
}