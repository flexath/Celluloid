package com.flexath.celluloid.data.movie_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.celluloid.data.model.repository.Repository
import com.flexath.celluloid.data.room.TvShowEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    // For tv show tab
    val trendingThisWeekTvShowList:MutableLiveData<com.flexath.celluloid.data.retrofit.tv_show.TvShow> = MutableLiveData()
    val onAirTodayTvShowList:MutableLiveData<com.flexath.celluloid.data.retrofit.tv_show.TvShow> = MutableLiveData()
    val topRatedTvShowList:MutableLiveData<com.flexath.celluloid.data.retrofit.tv_show.TvShow> = MutableLiveData()
    val searchTvShowList:MutableLiveData<com.flexath.celluloid.data.retrofit.tv_show.TvShow> = MutableLiveData()

    val detailsTvList:MutableLiveData<com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails> = MutableLiveData()
    val seasonTvShowList:MutableLiveData<com.flexath.celluloid.data.retrofit.tv_show.seasons.Season> = MutableLiveData()
    val creditsTvShowList:MutableLiveData<com.flexath.celluloid.data.retrofit.credits.Credits> = MutableLiveData()

    lateinit var tvShowFavouriteList: LiveData<List<TvShowEntity>>

    fun getAllTrendingThisWeekTvShow(api_key:String,language:String,popularity:String,air_date:String) = viewModelScope.launch {
        repository.getAllTrendingThisWeekTvShow(api_key,language,popularity,air_date).let {
            if(it.isSuccessful) {
                trendingThisWeekTvShowList.postValue(it.body())
            }else{
                Log.d("TvShowTrendingViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllOnAirTodayTvShow(api_key:String,language:String,popularity:String,air_date1:String,air_date2:String) = viewModelScope.launch {
        repository.getAllOnAirTodayTvShow(api_key,language,popularity,air_date1,air_date2).let {
            if(it.isSuccessful) {
                onAirTodayTvShowList.postValue(it.body())
            }else{
                Log.d("TvShowOnAirViewModel",it.errorBody().toString())
            }
        }
    }

    fun getAllTopRatedTvShow(api_key:String,language:String,vote_count:Int,vote_average:String,page:Int) = viewModelScope.launch {
        repository.getAllTopRatedTvShow(api_key,language,vote_count,vote_average,page).let {
            if(it.isSuccessful) {
                topRatedTvShowList.postValue(it.body())
            }else{
                Log.d("TvShowTopRatedViewModel",it.errorBody().toString())
            }
        }
    }

    fun getTvSearchResults(api_key:String,tv_title:String) = viewModelScope.launch {
        repository.getTvSearchResults(api_key,tv_title).let {
            if(it.isSuccessful) {
                searchTvShowList.postValue(it.body())
            }else{
                Log.d("TvShowSearchViewModel",it.errorBody().toString())
            }
        }
    }

    fun getTvShowDetails(tv_id:Int,api_key:String) = viewModelScope.launch {
        repository.getTvShowDetails(tv_id,api_key).let {
            if(it.isSuccessful) {
                detailsTvList.postValue(it.body())
            }else{
                Log.d("TvShowDetailsViewModel",it.errorBody().toString())
            }
        }
    }

    fun getTvShowSeason(tv_id:Int,season_number:Int,api_key:String) = viewModelScope.launch {
        repository.getTvShowSeason(tv_id,season_number,api_key).let {
            if(it.isSuccessful) {
                seasonTvShowList.postValue(it.body())
            }else{
                Log.d("TvShowSeasonViewModel",it.errorBody().toString())
            }
        }
    }

    fun getTvShowSeasonCredits(tv_id:Int,season_number:Int,api_key:String) = viewModelScope.launch {
        repository.getTvShowSeasonCredits(tv_id,season_number,api_key).let {
            if(it.isSuccessful) {
                creditsTvShowList.postValue(it.body())
            }else{
                Log.d("TvShowCreditsViewModel",it.errorBody().toString())
            }
        }
    }

    fun insertTvShowFavourite(result:TvShowEntity) = viewModelScope.launch {
        repository.insertTvShowFavourite(result)
    }

    fun deleteTvShowFavourite(result:TvShowEntity) = viewModelScope.launch {
        repository.deleteTvShowFavourite(result)
    }

    fun getAllTvShowFavourites() = viewModelScope.launch {
        tvShowFavouriteList = repository.getAllTvShowFavourites()
    }
}