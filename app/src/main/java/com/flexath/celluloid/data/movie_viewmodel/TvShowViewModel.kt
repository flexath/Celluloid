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
    val trendingThisWeekKDramaList:MutableLiveData<TvShow> = MutableLiveData()
    val onAirTodayKDramaList:MutableLiveData<TvShow> = MutableLiveData()

    val genreList:MutableLiveData<Genre> = MutableLiveData()

    fun getAllTrendingThisWeekKDrama(api_key:String,language:String,popularity:String,air_date:String) = viewModelScope.launch {
        repository.getAllTrendingThisWeekKDrama(api_key,language,popularity,air_date).let {
            if(it.isSuccessful) {
                trendingThisWeekKDramaList.postValue(it.body())
            }else{
                Log.d("UpComingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllOnAirTodayKDrama(api_key:String,language:String,popularity:String,air_date1:String,air_date2:String) = viewModelScope.launch {
        repository.getAllOnAirTodayKDrama(api_key,language,popularity,air_date1,air_date2).let {
            if(it.isSuccessful) {
                onAirTodayKDramaList.postValue(it.body())
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