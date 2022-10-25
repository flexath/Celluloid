package com.flexath.celluloid.data.movie_viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.celluloid.data.database.Genre
import com.flexath.celluloid.data.model.repository.MovieRepository
import com.flexath.celluloid.data.database.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel
@Inject constructor(private val repository: MovieRepository) : ViewModel() {

    val nowPlayingMovieList:MutableLiveData<Movie> = MutableLiveData()
    val upComingMovieList:MutableLiveData<Movie> = MutableLiveData()
    val popularMovieList:MutableLiveData<Movie> = MutableLiveData()
    val topRatedMovieList:MutableLiveData<Movie> = MutableLiveData()

    val genreList:MutableLiveData<Genre> = MutableLiveData()

    fun getAllNowPlayingMovies(api_key:String,release_date_sort:String,release_date:String,language:String) = viewModelScope.launch {
        repository.getAllNowPlayingMovies(api_key,release_date_sort,release_date,language).let {
            if(it.isSuccessful) {
                nowPlayingMovieList.postValue(it.body())
            }else{
                Log.d("NowPlayingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllUpComingMovies(api_key:String,release_date_sort:String,primary_release_date:String,language:String) = viewModelScope.launch {
        repository.getAllUpComingMovies(api_key,release_date_sort,primary_release_date,language).let {
            if(it.isSuccessful) {
                upComingMovieList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllPopularMovies(api_key:String) = viewModelScope.launch {
        repository.getAllPopularMovies(api_key).let {
            if(it.isSuccessful) {
                popularMovieList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllTopRatedMovies(api_key:String,vote_count:Int,vote_average:String,page:Int) = viewModelScope.launch {
        repository.getAllTopRatedMovies(api_key,vote_count,vote_average,page).let {
            if(it.isSuccessful) {
                topRatedMovieList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllGenres(api_key:String) = viewModelScope.launch {
        repository.getAllGenres(api_key).let {
            if(it.isSuccessful) {
                genreList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }
}