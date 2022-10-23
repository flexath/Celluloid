package com.flexath.celluloid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.Result
import com.flexath.celluloid.databinding.PopularRvBinding
import com.flexath.celluloid.databinding.TopRatedRvBinding

class FirstTopRatedAdapter(private val topRatedMovieList: ArrayList<Result>)
    : RecyclerView.Adapter<FirstTopRatedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = TopRatedRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = topRatedMovieList[position]
        holder.binding.apply {
            topRatedMovieTitle.text = item.title
            topRatedMovieLanguage.text = "  "+item.original_language
            topRatedMovieReleaseDate.text = "  "+item.release_date
            topRatedMoviePoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }
    }

    override fun getItemCount(): Int {
        return topRatedMovieList.size
    }

    inner class ViewHolder(val binding: TopRatedRvBinding) : RecyclerView.ViewHolder(binding.root)
}