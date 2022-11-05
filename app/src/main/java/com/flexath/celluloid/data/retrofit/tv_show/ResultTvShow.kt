package com.flexath.celluloid.data.retrofit.tv_show

import java.io.Serializable

data class ResultTvShow(
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String,
    val original_name: String,
    val original_language: String,
    val overview: String,
    val poster_path: String?
): Serializable