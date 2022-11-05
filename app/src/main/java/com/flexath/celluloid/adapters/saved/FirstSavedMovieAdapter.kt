package com.flexath.celluloid.adapters.saved

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.room.MovieEntity
import com.flexath.celluloid.databinding.SavedMovieRvBinding
import com.flexath.celluloid.ui.saved.SavedFirstFragmentDirections

class FirstSavedMovieAdapter(private val savedMovieList: List<MovieEntity>)
        :RecyclerView.Adapter<FirstSavedMovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = SavedMovieRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = savedMovieList[position]
        holder.binding.apply {
            savedMovieTitle.text = item.movie_title
            savedMovieReleaseDate.text = item.movie_releaseDate
            savedMoviePoster.load("https://image.tmdb.org/t/p/original"+item.movie_poster)
        }

        holder.itemView.setOnClickListener {
            val action = SavedFirstFragmentDirections.savedFirstToMovieSecondAction()
            action.result = item.movie_result
            it.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return savedMovieList.size
    }

    inner class ViewHolder(val binding:SavedMovieRvBinding) : RecyclerView.ViewHolder(binding.root)
}