package com.flexath.celluloid.data.room

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.flexath.celluloid.data.retrofit.movie.Result
import com.flexath.celluloid.data.retrofit.tv_show.ResultTvShow

@androidx.room.Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val movie_id:Int,
    @ColumnInfo(name = "movie_title") val movie_title:String?,
    @ColumnInfo(name = "movie_releaseDate") val movie_releaseDate:String?,
    @ColumnInfo(name = "movie_poster") val movie_poster:String?,
    @ColumnInfo(name = "movie_result") val movie_result:Result?
):java.io.Serializable

@androidx.room.Entity(tableName = "tv_table")
data class TvShowEntity(
    @PrimaryKey(autoGenerate = true) val tv_id:Int,
    @ColumnInfo(name = "tv_title") val tv_title:String?,
    @ColumnInfo(name = "tv_FirstAirDate") val tv_FirstAirDate:String?,
    @ColumnInfo(name = "tv_poster") val tv_poster:String?,
    @ColumnInfo(name = "tv_result") val tv_result:ResultTvShow?
):java.io.Serializable
