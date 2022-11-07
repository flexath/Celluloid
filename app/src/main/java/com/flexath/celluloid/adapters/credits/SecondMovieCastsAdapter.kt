package com.flexath.celluloid.adapters.credits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.adapters.CreditSwitch.Companion.switchOnMovie
import com.flexath.celluloid.databinding.MovieCastsRvBinding
import com.flexath.celluloid.ui.movie.MovieSecondFragmentDirections
import com.flexath.celluloid.ui.tvshow.TvShowFourthFragmentDirections

class SecondMovieCastsAdapter(private val castsMovieList: ArrayList<com.flexath.celluloid.data.retrofit.credits.Cast>)
        :RecyclerView.Adapter<SecondMovieCastsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val items = MovieCastsRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(items)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = castsMovieList[position]
        holder.binding.apply {
            castName.text = item.original_name
            castCharacterName.text = "( " + item.character + " )"
            castProfilePicture.load("https://image.tmdb.org/t/p/original"+item.profile_path)
        }

        holder.itemView.setOnClickListener {

            if(switchOnMovie) {
                val action = MovieSecondFragmentDirections.movieSecondToPersonAction()
                action.personCast = item
                action.personString = "cast"
                it.findNavController().navigate(action)
            }else{
                val action = TvShowFourthFragmentDirections.tvShowFourthToPersonAction()
                action.personCast = item
                action.personString = "cast"
                it.findNavController().navigate(action)
            }

        }
    }

    override fun getItemCount(): Int {
        return castsMovieList.size
    }

    inner class ViewHolder(val binding:MovieCastsRvBinding) : RecyclerView.ViewHolder(binding.root)
}