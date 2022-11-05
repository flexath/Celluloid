package com.flexath.celluloid.data.retrofit.movie

import java.io.Serializable

data class Result(
    val id: Int,
    val original_language: String,
    val title: String,
    val overview: String,
    val poster_path: String?,
    val release_date: String,
    val genre_ids:List<Int>?          // Must be List type
):Serializable