package com.flexath.celluloid.data.retrofit.tv_show.seasons

data class Episode(
    val air_date: String,
    val crew: List<com.flexath.celluloid.data.retrofit.tv_show.seasons.Crew>,
    val episode_number: Int,
    val guest_stars: List<com.flexath.celluloid.data.retrofit.tv_show.seasons.GuestStar>,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Int,
    val season_number: Int,
    val show_id: Int,
    val still_path: String?,
)