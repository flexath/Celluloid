package com.flexath.celluloid.adapters.movie.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.movie.Result
import com.flexath.celluloid.databinding.TvSearchRvBinding
import com.flexath.celluloid.ui.tvshow.TvShowSearchFragmentDirections

class FirstMovieSearchAdapter(private val searchMovieList: ArrayList<Result>)
        :RecyclerView.Adapter<FirstMovieSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TvSearchRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchMovieList[position]
        holder.binding.apply {
            tvShowSearchTitle.text = item.title
            tvShowSearchFirstOnAirDate.text = item.release_date
            tvShowSearchPoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
            day.text = "Release Date"
        }

        holder.itemView.setOnClickListener {
            val action = TvShowSearchFragmentDirections.movieSearchToSecondAction()
            action.result = item
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return searchMovieList.size
    }

    inner class ViewHolder(val binding:TvSearchRvBinding) : RecyclerView.ViewHolder(binding.root)
}