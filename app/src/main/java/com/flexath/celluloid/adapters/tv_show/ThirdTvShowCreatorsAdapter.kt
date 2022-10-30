package com.flexath.celluloid.adapters.tv_show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.details.tv_show.CreatedBy
import com.flexath.celluloid.data.database.details.tv_show.TvShowDetails
import com.flexath.celluloid.data.database.tv_show.ResultTvShow
import com.flexath.celluloid.databinding.TvCreatorsRvBinding
import com.flexath.celluloid.databinding.TvTrendingRvBinding
import com.flexath.celluloid.ui.tvshow.TvShowSecondFragmentDirections

class ThirdTvShowCreatorsAdapter(private val creatorsTvShowList: List<CreatedBy>)
        :RecyclerView.Adapter<ThirdTvShowCreatorsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvCreatorsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = creatorsTvShowList[position]
        holder.binding.apply {
            tvShowDirectorName.text = item.name
            tvShowCreatorProfilePicture.load("https://image.tmdb.org/t/p/original"+item.profile_path)
        }
    }

    override fun getItemCount(): Int {
        return creatorsTvShowList.size
    }

    inner class ViewHolder(val binding:TvCreatorsRvBinding) : RecyclerView.ViewHolder(binding.root)
}