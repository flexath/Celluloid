package com.flexath.celluloid.data.database.people

data class Person(
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: Any,
    val gender: Int,
    val homepage: String,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val place_of_birth: String,
    val profile_path: String?
)