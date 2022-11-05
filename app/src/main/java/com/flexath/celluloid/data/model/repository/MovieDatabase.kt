package com.flexath.celluloid.data.model.repository

import com.flexath.celluloid.data.URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDatabase {

    @Provides
    fun provideUrl() : String = URL.base_url

    @Provides
    @Singleton
    fun providePopularRetrofitInstance(url:String) : MovieApi{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

}