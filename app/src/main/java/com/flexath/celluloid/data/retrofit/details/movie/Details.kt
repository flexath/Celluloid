package com.flexath.celluloid.data.retrofit.details.movie

data class Details(
    val budget: Int,
    val genres: List<com.flexath.celluloid.data.retrofit.details.movie.Genre>,
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val production_companies: List<com.flexath.celluloid.data.retrofit.details.movie.ProductionCompany>,
    val production_countries: List<com.flexath.celluloid.data.retrofit.details.movie.ProductionCountry>,
    val release_date: String?,
    val revenue: Double,
    val runtime: Int,
    val spoken_languages: List<com.flexath.celluloid.data.retrofit.details.movie.SpokenLanguage>,
    val status: String?,
    val tagline: String?
)