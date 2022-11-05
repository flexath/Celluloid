package com.flexath.celluloid.data.model.repository

import com.flexath.celluloid.data.retrofit.credits.Credits
import com.flexath.celluloid.data.retrofit.details.movie.Details
import com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails
import com.flexath.celluloid.data.retrofit.movie.Movie
import com.flexath.celluloid.data.retrofit.people.Person
import com.flexath.celluloid.data.retrofit.tv_show.TvShow
import com.flexath.celluloid.data.retrofit.tv_show.seasons.Season
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getAllNowPlayingMovies(
        @Query("api_key") api_key:String,
        @Query("sort_by") release_date_sort:String,
        @Query("release_date.gte") release_date:String,
        @Query("with_original_language") language:String
    ) : Response<com.flexath.celluloid.data.retrofit.movie.Movie>

    @GET("discover/movie")
    suspend fun getAllUpComingMovies(
        @Query("api_key") api_key:String,
        @Query("sort_by") release_date_sort:String,
        @Query("primary_release_date.gte") primary_release_date:String,
        @Query("with_original_language") language:String
    ) : Response<com.flexath.celluloid.data.retrofit.movie.Movie>

    @GET("discover/movie")
    suspend fun getAllPopularMovies(
        @Query("api_key") api_key:String
    ) : Response<com.flexath.celluloid.data.retrofit.movie.Movie>

    @GET("discover/movie")
    suspend fun getAllTopRatedMovies(
        @Query("api_key") api_key:String,
        @Query("vote_count.gte") vote_count:Int,
        @Query("sort_by") vote_average:String,
        @Query("page") page:Int
    ) : Response<com.flexath.celluloid.data.retrofit.movie.Movie>

    @GET("search/movie")
    suspend fun getMovieSearchResults(
        @Query("api_key") api_key:String,
        @Query("query") tv_title:String
    ) : Response<com.flexath.celluloid.data.retrofit.movie.Movie>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key:String
    ) : Response<com.flexath.celluloid.data.retrofit.credits.Credits>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key:String
    ) : Response<com.flexath.celluloid.data.retrofit.details.movie.Details>

    @GET("person/{person_id}")
    suspend fun getMoviePerson(
        @Path("person_id") person_id:Int,
        @Query("api_key") api_key:String
    ) : Response<com.flexath.celluloid.data.retrofit.people.Person>

    ////////////////////////////////////////////////////////////////
    ////////////////////// -- Tv Show -- ///////////////////////////

    @GET("discover/tv")
    suspend fun getAllTrendingThisWeekTvShow(
        @Query("api_key") api_key:String,
        @Query("with_original_language") language:String,
        @Query("sort_by") popularity:String,
        @Query("air_date.gte") air_date:String
    ) : Response<com.flexath.celluloid.data.retrofit.tv_show.TvShow>

    @GET("discover/tv")
    suspend fun getAllOnAirTodayTvShow(
        @Query("api_key") api_key:String,
        @Query("with_original_language") language:String,
        @Query("sort_by") popularity:String,
        @Query("air_date.gte") air_date1:String,
        @Query("air_date.lte") air_date2:String
    ) : Response<com.flexath.celluloid.data.retrofit.tv_show.TvShow>

    @GET("discover/tv")
    suspend fun getAllTopRatedTvShow(
        @Query("api_key") api_key:String,
        @Query("with_original_language") language:String,
        @Query("vote_count.gte") vote_count:Int,
        @Query("sort_by") vote_average:String,
        @Query("page") page:Int
    ) : Response<com.flexath.celluloid.data.retrofit.tv_show.TvShow>

    @GET("search/tv")
    suspend fun getTvSearchResults(
        @Query("api_key") api_key:String,
        @Query("query") tv_title:String
    ) : Response<com.flexath.celluloid.data.retrofit.tv_show.TvShow>

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetails(
        @Path("tv_id") tv_id:Int,
        @Query("api_key") api_key:String
    ) : Response<com.flexath.celluloid.data.retrofit.details.tv_show.TvShowDetails>

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getTvShowSeason(
        @Path("tv_id") tv_id:Int,
        @Path("season_number") season_number:Int,
        @Query("api_key") api_key:String
    ) : Response<com.flexath.celluloid.data.retrofit.tv_show.seasons.Season>

    @GET("tv/{tv_id}/season/{season_number}/credits")
    suspend fun getTvShowSeasonCredits(
        @Path("tv_id") tv_id:Int,
        @Path("season_number") season_number:Int,
        @Query("api_key") api_key:String
    ) : Response<com.flexath.celluloid.data.retrofit.credits.Credits>

}