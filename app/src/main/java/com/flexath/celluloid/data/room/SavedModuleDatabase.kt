package com.flexath.celluloid.data.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SavedModuleDatabase {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context) = Room.databaseBuilder(
        context.applicationContext,
        SavedDatabaseDao::class.java,
        "SavedDatabase"
    ).build()

    @Provides
    @Singleton
    fun getDatabaseDao(db:SavedDatabaseDao) = db.provideDao()
}