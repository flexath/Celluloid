package com.flexath.celluloid.data.database.details

data class Details(
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String?,
    val revenue: Double,
    val runtime: Int,
    val spoken_languages: List<SpokenLanguage>,
    val status: String?,
    val tagline: String?
)