package com.flexath.celluloid.data.model.repository

import javax.inject.Inject

class MovieRepository
@Inject constructor(private val api:MovieApi) {

    suspend fun getAllNowPlayingMovies(api_key:String,release_date_sort:String,release_date:String,language:String) =
        api.getAllNowPlayingMovies(api_key,release_date_sort,release_date,language)

    suspend fun getAllUpComingMovies(api_key:String,release_date_sort:String,primary_release_date:String,language:String) =
        api.getAllUpComingMovies(api_key,release_date_sort,primary_release_date,language)

    suspend fun getAllPopularMovies(api_key:String) =
        api.getAllPopularMovies(api_key)

    suspend fun getAllTopRatedMovies(api_key:String,vote_count:Int,vote_average:String,page:Int) =
        api.getAllTopRatedMovies(api_key,vote_count,vote_average,page)

    suspend fun getAllGenres(api_key:String) =
        api.getAllGenres(api_key)

    //Followings are for tv shows
    suspend fun getAllTrendingThisWeekKDrama(api_key:String,language:String,popularity:String,air_date:String) =
        api.getAllTrendingThisWeekKDrama(api_key,language,popularity,air_date)

    suspend fun getAllOnAirTodayKDrama(api_key:String,language:String,popularity:String,air_date1:String,air_date2:String) =
        api.getAllOnAirTodayKDrama(api_key,language,popularity,air_date1,air_date2)
}