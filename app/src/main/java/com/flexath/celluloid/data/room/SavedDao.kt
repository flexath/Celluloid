package com.flexath.celluloid.data.room

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@androidx.room.Dao
interface SavedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieFavourite(result:MovieEntity)

    @Delete
    suspend fun deleteMovieFavourite(result:MovieEntity)

    @Query("SELECT * FROM MOVIE_TABLE ORDER BY movie_id ASC")
    fun getAllMovieFavourites() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM MOVIE_TABLE WHERE movie_id= :movieId")
    fun getMovieById(movieId:Int) : LiveData<MovieEntity>


    // Following is Tv Shows
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowFavourite(result:TvShowEntity)

    @Delete
    suspend fun deleteTvShowFavourite(result:TvShowEntity)


    @Query("SELECT * FROM tv_table ORDER BY tv_id ASC")
    fun getAllTvShowFavourites() : LiveData<List<TvShowEntity>>


}