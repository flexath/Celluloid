package com.flexath.celluloid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.Result
import com.flexath.celluloid.databinding.UpComingRvBinding

class FirstUpComingAdapter(private val upComingMovieList: ArrayList<Result>)
    : RecyclerView.Adapter<FirstUpComingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = UpComingRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = upComingMovieList[position]
        holder.binding.apply {
            upComingMovieTitle.text = item.title
            upComingMovieLanguage.text = "  "+item.original_language
            upComingMovieReleaseDate.text = "  "+item.release_date
            upComingMoviePoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }
    }

    override fun getItemCount(): Int {
        return upComingMovieList.size
    }

    inner class ViewHolder(val binding: UpComingRvBinding) : RecyclerView.ViewHolder(binding.root)
}