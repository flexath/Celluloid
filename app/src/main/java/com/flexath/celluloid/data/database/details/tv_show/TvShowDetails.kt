package com.flexath.celluloid.data.database.details.tv_show

data class TvShowDetails(
    val id: Int,
    val poster_path: String?,
    val name: String?,
    val first_air_date: String?,
    val last_air_date: String?,
    val episode_run_time: List<Int>?,         // runtime per episode
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val genres: List<Genre>?,
    val overview: String?,

    // In Bottom Dialog
    val original_language: String,
    val status: String,
    val tagline: String,
    val homepage: String,
    val type: String,
    val networks: List<Network>,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,

    val spoken_languages: List<SpokenLanguage>,

    // In Credits
    val created_by: List<CreatedBy>,

    val seasons: List<Season>,
    val last_episode_to_air: LastEpisodeToAir
)