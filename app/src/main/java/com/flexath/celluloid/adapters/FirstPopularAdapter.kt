package com.flexath.celluloid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.Result
import com.flexath.celluloid.databinding.PopularRvBinding

class FirstPopularAdapter(private val popularMovieList: ArrayList<Result>)
    : RecyclerView.Adapter<FirstPopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = PopularRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = popularMovieList[position]
        holder.binding.apply {
            popularMovieTitle.text = item.title
            popularMovieLanguage.text = "  "+item.original_language
            popularMovieReleaseDate.text = "  "+item.release_date
            popularMoviePoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }
    }

    override fun getItemCount(): Int {
        return popularMovieList.size
    }

    inner class ViewHolder(val binding: PopularRvBinding) : RecyclerView.ViewHolder(binding.root)
}