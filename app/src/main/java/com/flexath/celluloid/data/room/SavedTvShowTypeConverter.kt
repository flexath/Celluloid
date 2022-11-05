package com.flexath.celluloid.data.room

import androidx.room.TypeConverter
import com.flexath.celluloid.data.retrofit.tv_show.ResultTvShow
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// I just copied this class from stackoverflow and change a little bit
class SavedTvShowTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun tvShowResultToString(result: ResultTvShow): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToTvShowResult(resultString: String): ResultTvShow {
        val objectType = object : TypeToken<ResultTvShow>() {}.type
        return gson.fromJson(resultString,objectType)
    }

}