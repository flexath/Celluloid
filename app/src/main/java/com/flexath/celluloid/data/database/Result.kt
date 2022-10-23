package com.flexath.celluloid.data.database

data class Result(
    val id: Int,
    val original_language: String,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
)