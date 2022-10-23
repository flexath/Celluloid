package com.flexath.celluloid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.Result
import com.flexath.celluloid.databinding.NowPlayingRvBinding

class FirstNowPlayingAdapter(private val nowPlayingMovieList: ArrayList<Result>)
        :RecyclerView.Adapter<FirstNowPlayingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = NowPlayingRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = nowPlayingMovieList[position]
        holder.binding.apply {
            nowPlayingMovieTitle.text = "  "+item.title
            nowPlayingMovieReleaseDate.text = "  "+item.release_date
            nowPlayingMoviePoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }
    }

    override fun getItemCount(): Int {
        return nowPlayingMovieList.size
    }

    inner class ViewHolder(val binding: NowPlayingRvBinding) : RecyclerView.ViewHolder(binding.root)
}