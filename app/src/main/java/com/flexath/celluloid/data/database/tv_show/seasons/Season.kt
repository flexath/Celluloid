package com.flexath.celluloid.data.database.tv_show.seasons

data class Season(
    val air_date: String,
    val episodes: List<Episode>,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int
)