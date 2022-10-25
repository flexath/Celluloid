package com.flexath.celluloid.data.model.repository

import com.flexath.celluloid.data.database.Genre
import com.flexath.celluloid.data.database.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    suspend fun getAllNowPlayingMovies(
        @Query("api_key") api_key:String,
        @Query("sort_by") release_date_sort:String,
        @Query("release_date.gte") release_date:String,
        @Query("with_original_language") language:String
    ) : Response<Movie>

    @GET("discover/movie")
    suspend fun getAllUpComingMovies(
        @Query("api_key") api_key:String,
        @Query("sort_by") release_date_sort:String,
        @Query("primary_release_date.gte") primary_release_date:String,
        @Query("with_original_language") language:String
    ) : Response<Movie>

    @GET("discover/movie")
    suspend fun getAllPopularMovies(
        @Query("api_key") api_key:String
    ) : Response<Movie>

    @GET("discover/movie")
    suspend fun getAllTopRatedMovies(
        @Query("api_key") api_key:String,
        @Query("vote_count.gte") vote_count:Int,
        @Query("sort_by") vote_average:String,
        @Query("page") page:Int
    ) : Response<Movie>

    @GET("genre/movie/list")
    suspend fun getAllGenres(
        @Query("api_key") api_key:String
    ) : Response<Genre>
}