package com.flexath.celluloid.data.retrofit.credits

import java.io.Serializable

data class Crew(
    val adult: Boolean,
    val department: String,
    val gender: Int,
    val id: Int,
    val job: String?,
    val known_for_department: String,
    val name: String,
    val original_name: String?,
    val popularity: Double,
    val profile_path: String?
):Serializable