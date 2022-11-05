package com.flexath.celluloid.data.model.repository

import com.flexath.celluloid.data.room.SavedDao
import javax.inject.Inject
import com.flexath.celluloid.data.retrofit.movie.Result
import com.flexath.celluloid.data.retrofit.tv_show.ResultTvShow
import com.flexath.celluloid.data.room.MovieEntity
import com.flexath.celluloid.data.room.TvShowEntity

class Repository
@Inject constructor(private val api:MovieApi,private val dao:SavedDao) {

    suspend fun getAllNowPlayingMovies(api_key:String,release_date_sort:String,release_date:String,language:String) =
        api.getAllNowPlayingMovies(api_key,release_date_sort,release_date,language)

    suspend fun getAllUpComingMovies(api_key:String,release_date_sort:String,primary_release_date:String,language:String) =
        api.getAllUpComingMovies(api_key,release_date_sort,primary_release_date,language)

    suspend fun getAllPopularMovies(api_key:String) =
        api.getAllPopularMovies(api_key)

    suspend fun getAllTopRatedMovies(api_key:String,vote_count:Int,vote_average:String,page:Int) =
        api.getAllTopRatedMovies(api_key,vote_count,vote_average,page)

    suspend fun getMovieSearchResults(api_key:String,tv_title:String) =
        api.getMovieSearchResults(api_key,tv_title)

    suspend fun getMovieCredits(movie_id:Int,api_key:String) =
        api.getMovieCredits(movie_id,api_key)

    suspend fun getMovieDetails(movie_id:Int,api_key:String) =
        api.getMovieDetails(movie_id,api_key)

    suspend fun getMoviePerson(person_id:Int,api_key:String) =
        api.getMoviePerson(person_id,api_key)

    suspend fun insertMovieFavourite(result:MovieEntity) =
        dao.insertMovieFavourite(result)

    suspend fun deleteMovieFavourite(result:MovieEntity) =
        dao.deleteMovieFavourite(result)

    fun getAllMovieFavourites() = dao.getAllMovieFavourites()

    //Followings are for tv shows
    suspend fun getAllTrendingThisWeekTvShow(api_key:String,language:String,popularity:String,air_date:String) =
        api.getAllTrendingThisWeekTvShow(api_key,language,popularity,air_date)

    suspend fun getAllOnAirTodayTvShow(api_key:String,language:String,popularity:String,air_date1:String,air_date2:String) =
        api.getAllOnAirTodayTvShow(api_key,language,popularity,air_date1,air_date2)

    suspend fun getAllTopRatedTvShow(api_key:String,language:String,vote_count:Int,vote_average:String,page:Int) =
        api.getAllTopRatedTvShow(api_key,language,vote_count,vote_average,page)

    suspend fun getTvSearchResults(api_key:String,tv_title:String) =
        api.getTvSearchResults(api_key,tv_title)

    suspend fun getTvShowDetails(tv_id:Int,api_key:String) =
        api.getTvShowDetails(tv_id,api_key)

    suspend fun getTvShowSeason(tv_id:Int,season_number:Int,api_key:String) =
        api.getTvShowSeason(tv_id,season_number,api_key)

    suspend fun getTvShowSeasonCredits(tv_id:Int,season_number:Int,api_key:String) =
        api.getTvShowSeasonCredits(tv_id,season_number,api_key)

    suspend fun insertTvShowFavourite(result:TvShowEntity) =
        dao.insertTvShowFavourite(result)

    suspend fun deleteTvShowFavourite(result:TvShowEntity) =
        dao.deleteTvShowFavourite(result)

    fun getAllTvShowFavourites() = dao.getAllTvShowFavourites()
}