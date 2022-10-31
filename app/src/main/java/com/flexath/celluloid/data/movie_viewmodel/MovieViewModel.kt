package com.flexath.celluloid.data.movie_viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.celluloid.data.database.credits.Credits
import com.flexath.celluloid.data.database.details.movie.Details
import com.flexath.celluloid.data.database.movie.Movie
import com.flexath.celluloid.data.database.people.Person
import com.flexath.celluloid.data.model.repository.MovieRepository
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
    var searchMovieList:MutableLiveData<Movie> = MutableLiveData()

    val creditsMovieList:MutableLiveData<Credits> = MutableLiveData()
    val detailsMovieList:MutableLiveData<Details> = MutableLiveData()
    val personMovieList:MutableLiveData<Person> = MutableLiveData()

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

    fun getMovieSearchResults(api_key:String,tv_title:String) = viewModelScope.launch {
        repository.getMovieSearchResults(api_key,tv_title).let {
            if(it.isSuccessful) {
                searchMovieList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getMovieCredits(movie_id:Int,api_key:String) = viewModelScope.launch {
        repository.getMovieCredits(movie_id,api_key).let {
            if(it.isSuccessful) {
                creditsMovieList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getMovieDetails(movie_id:Int,api_key:String) = viewModelScope.launch {
        repository.getMovieDetails(movie_id,api_key).let {
            if(it.isSuccessful) {
                detailsMovieList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getMoviePerson(person_id:Int,api_key:String) = viewModelScope.launch {
        repository.getMoviePerson(person_id,api_key).let {
            if(it.isSuccessful) {
                personMovieList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }
}