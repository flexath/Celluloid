package com.flexath.celluloid.adapters.movie.second

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.flexath.celluloid.data.database.credits.Cast
import com.flexath.celluloid.databinding.MovieCastsRvBinding

class SecondMovieCastsAdapter(private val castsMovieList: ArrayList<Cast>)
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

//        holder.itemView.setOnClickListener {
//            val action = MovieSearchFragmentDirections.movieSearchToSecondAction()
//            action.result = item
//            it.findNavController().navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return castsMovieList.size
    }

    inner class ViewHolder(val binding:MovieCastsRvBinding) : RecyclerView.ViewHolder(binding.root)
}