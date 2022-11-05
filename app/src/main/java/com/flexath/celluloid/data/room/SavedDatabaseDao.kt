package com.flexath.celluloid.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MovieEntity::class,TvShowEntity::class], version = 1 , exportSchema = true)
@TypeConverters(SavedMovieTypeConverter::class,SavedTvShowTypeConverter::class)
abstract class SavedDatabaseDao : RoomDatabase() {

    abstract fun provideDao():SavedDao
}