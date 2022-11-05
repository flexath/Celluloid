package com.flexath.celluloid.data.retrofit.details.tv_show

data class TvShowDetails(
    val id: Int,
    val poster_path: String?,
    val name: String?,
    val first_air_date: String?,
    val last_air_date: String?,
    val episode_run_time: List<Int>?,         // runtime per episode
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val genres: List<com.flexath.celluloid.data.retrofit.details.tv_show.Genre>?,
    val overview: String?,

    // In Bottom Dialog
    val original_language: String,
    val status: String,
    val tagline: String,
    val homepage: String,
    val type: String,
    val networks: List<com.flexath.celluloid.data.retrofit.details.tv_show.Network>,
    val production_companies: List<com.flexath.celluloid.data.retrofit.details.tv_show.ProductionCompany>,
    val production_countries: List<com.flexath.celluloid.data.retrofit.details.tv_show.ProductionCountry>,

    val spoken_languages: List<com.flexath.celluloid.data.retrofit.details.tv_show.SpokenLanguage>,

    // In Credits
    val created_by: List<com.flexath.celluloid.data.retrofit.details.tv_show.CreatedBy>,

    val seasons: List<com.flexath.celluloid.data.retrofit.details.tv_show.Season>,
    val last_episode_to_air: com.flexath.celluloid.data.retrofit.details.tv_show.LastEpisodeToAir
)