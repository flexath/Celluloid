package com.flexath.celluloid.adapters.movie.second

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.credits.Crew
import com.flexath.celluloid.databinding.MovieCrewsRvBinding
import com.flexath.celluloid.ui.movie.MovieSecondFragmentDirections

class SecondMovieCrewsAdapter(private val crewsMovieList: ArrayList<Crew>)
        :RecyclerView.Adapter<SecondMovieCrewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = MovieCrewsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = crewsMovieList[position]
        holder.binding.apply {
            crewName.text = item.original_name
            crewJobName.text = "( " + item.job + " )"
            crewProfilePicture.load("https://image.tmdb.org/t/p/original"+item.profile_path)
        }

        holder.itemView.setOnClickListener {
            val action = MovieSecondFragmentDirections.movieSecondToPersonAction()
            action.personCrew = item
            action.personString = "crew"
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return crewsMovieList.size
    }

    inner class ViewHolder(val binding:MovieCrewsRvBinding) : RecyclerView.ViewHolder(binding.root)
}