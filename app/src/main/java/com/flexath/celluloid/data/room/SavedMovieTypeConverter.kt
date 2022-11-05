package com.flexath.celluloid.data.room

import androidx.room.TypeConverter
import com.flexath.celluloid.data.retrofit.movie.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// I just copied this class from stackoverflow and change a little bit
class SavedMovieTypeConverter {

    val gson = Gson()

    @TypeConverter
    fun resultToString(result:Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(resultString: String): Result {
        val objectType = object : TypeToken<Result>() {}.type
        return gson.fromJson(resultString,objectType)
    }

}