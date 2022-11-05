package com.flexath.celluloid.adapters.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.room.TvShowEntity
import com.flexath.celluloid.databinding.SavedTvShowRvBinding
import com.flexath.celluloid.ui.saved.SavedFirstFragmentDirections

class FirstSavedTvShowAdapter(private val savedTvShowList: List<TvShowEntity>)
        :RecyclerView.Adapter<FirstSavedTvShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = SavedTvShowRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = savedTvShowList[position]
        holder.binding.apply {
            savedTvShowTitle.text = item.tv_title
            savedTvShowFirstOnAirDate.text = item.tv_FirstAirDate
            savedTvShowPoster.load("https://image.tmdb.org/t/p/original"+item.tv_poster)
        }

        holder.itemView.setOnClickListener {
            val action = SavedFirstFragmentDirections.savedFirstToTvShowThirdAction()
            action.tvShowResult = item.tv_result
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return savedTvShowList.size
    }

    inner class ViewHolder(val binding:SavedTvShowRvBinding) : RecyclerView.ViewHolder(binding.root)
}