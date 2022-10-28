package com.flexath.celluloid.adapters.movie.first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.movie.Result
import com.flexath.celluloid.databinding.MovieSearchRvBinding
import com.flexath.celluloid.databinding.TvSearchRvBinding
import com.flexath.celluloid.ui.movie.MovieSearchFragmentDirections
import com.flexath.celluloid.ui.tvshow.TvShowSearchFragmentDirections

class FirstMovieSearchAdapter(private val searchMovieList: ArrayList<Result>)
        :RecyclerView.Adapter<FirstMovieSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = MovieSearchRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchMovieList[position]
        holder.binding.apply {
            movieSearchTitle.text = item.title
            movieSearchReleaseDate.text = item.release_date
            movieSearchPoster.load("https://image.tmdb.org/t/p/original"+item.poster_path)
        }

        holder.itemView.setOnClickListener {
            val action = MovieSearchFragmentDirections.movieSearchToSecondAction()
            action.result = item
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return searchMovieList.size
    }

    inner class ViewHolder(val binding:MovieSearchRvBinding) : RecyclerView.ViewHolder(binding.root)
}