package com.flexath.celluloid.data.movie_viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.celluloid.data.database.Genre
import com.flexath.celluloid.data.database.tv_show.TvShow
import com.flexath.celluloid.data.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject constructor(private val repository: MovieRepository) : ViewModel() {

    // For tv show tab
    val trendingThisWeekTvShowList:MutableLiveData<TvShow> = MutableLiveData()
    val onAirTodayTvShowList:MutableLiveData<TvShow> = MutableLiveData()
    val topRatedTvShowList:MutableLiveData<TvShow> = MutableLiveData()
    val searchTvShowList:MutableLiveData<TvShow> = MutableLiveData()

    val genreList:MutableLiveData<Genre> = MutableLiveData()

    fun getAllTrendingThisWeekTvShow(api_key:String,language:String,popularity:String,air_date:String) = viewModelScope.launch {
        repository.getAllTrendingThisWeekTvShow(api_key,language,popularity,air_date).let {
            if(it.isSuccessful) {
                trendingThisWeekTvShowList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllOnAirTodayTvShow(api_key:String,language:String,popularity:String,air_date1:String,air_date2:String) = viewModelScope.launch {
        repository.getAllOnAirTodayTvShow(api_key,language,popularity,air_date1,air_date2).let {
            if(it.isSuccessful) {
                onAirTodayTvShowList.postValue(it.body())
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

    fun getAllTopRatedTvShow(api_key:String,language:String,vote_count:Int,vote_average:String,page:Int) = viewModelScope.launch {
        repository.getAllTopRatedTvShow(api_key,language,vote_count,vote_average,page).let {
            if(it.isSuccessful) {
                topRatedTvShowList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getTvSearchResults(api_key:String,tv_title:String) = viewModelScope.launch {
        repository.getTvSearchResults(api_key,tv_title).let {
            if(it.isSuccessful) {
                searchTvShowList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }
}